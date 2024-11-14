package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import sa.cancellation.CostFree;
import sa.cancellation.ICancellationPolicy;
import sa.properties.Property;
import sa.observer.interfaces.INotifyObserver;
import sa.users.Tenant;



public class Booking implements INotifyConfiguration {

	private LocalDate				checkIn;
	private LocalDate				checkOut;
	private Property				property;
	private Pricer					pricer;
	private Tenant 					tenant;
	private BookingSchedule			schedule;

	private IReserveState			state;
	private ICancellationPolicy		policy;

	private List<PaymentMethod>		paymentMethods;
	private List<INotifyObserver>	obsBasePrice;
	
	private HashMap<LocalDate, Set<INotifyObserver>>    obsBP;
	private HashMap<LocalDate, Set<INotifyObserver>>	obsSP;
	private HashMap<BookedPeriod, Set<INotifyObserver>> obsCancel;
	private HashMap<BookedPeriod, Set<INotifyObserver>> obsReserve;


	public Booking(Property property, LocalDate checkIn, LocalDate checkOut, List<PaymentMethod> paymentMethods,
			double pricePerDayWeekday, List<SpecialPeriod> periods) {
		// TODO Auto-generated constructor stub
		this.state 				= new ReserveAvailable();
		this.pricer		 		= new Pricer(pricePerDayWeekday, periods);
		this.policy				= new CostFree();
		this.paymentMethods		= paymentMethods;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.property			= property;
		this.obsBasePrice		= new ArrayList<INotifyObserver>();
		this.obsSP 				= new HashMap<LocalDate, Set<INotifyObserver>>();
		this.obsBP 				= new HashMap<LocalDate, Set<INotifyObserver>>();
		this.obsCancel  		= new HashMap<BookedPeriod, Set<INotifyObserver>>();
		this.obsReserve  		= new HashMap<BookedPeriod, Set<INotifyObserver>>();
		this.schedule  			= new BookingSchedule();
	}

	// Para hacer DOC del state available
	public Booking(ReserveAvailable stateAvailable, CostFree policy, Pricer pricer, Property property, LocalDate checkIn,
			LocalDate checkOut,	List<PaymentMethod> paymentMethods, double pricePerDayWeekday, List<SpecialPeriod> periods,
			HashMap<LocalDate, Set<INotifyObserver>> obsSP, HashMap<LocalDate, Set<INotifyObserver>> obsBP,
			HashMap<BookedPeriod, Set<INotifyObserver>> obsCancel, HashMap<BookedPeriod, Set<INotifyObserver>> obsReserve,
			BookingSchedule schedule, List<INotifyObserver> obsBasePrice) {
		// TODO Auto-generated constructor stub
		this.state 			= stateAvailable;
		this.pricer 		= pricer;
		this.pricer.setBasePrice(pricePerDayWeekday);
		periods.stream().forEach(p -> this.pricer.addSpecialPeriod(p));
		this.policy			= policy;
		this.paymentMethods	= paymentMethods;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.property		= property;
		this.obsSP 			= obsSP;
		this.obsBP 			= obsBP;
		this.obsCancel		= obsCancel;
		this.obsReserve		= obsReserve;
		this.schedule  		= schedule;
		this.obsBasePrice	= obsBasePrice;
	}

	public IReserveState getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	public void setCancellationPolicy(ICancellationPolicy policy) {
		// TODO Auto-generated method stub
		this.policy = policy;
	}

	public ICancellationPolicy getPolicy() {
		// TODO Auto-generated method stub
		return this.policy;
	}

	public void applyPolicy(BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.policy.activate(LocalDate.now(), this, bp);
	}

	public void setBasePrice(double newPrice) {
		// TODO Auto-generated method stub
		double currBP = this.pricer.getBasePrice();
		this.pricer.setBasePrice(newPrice);
		if (currBP > newPrice) {
			// notificá
			this.obsBasePrice.stream().forEach(o -> o.update(this));
		} 		
	}

//	public void setSPPrice(double newPrice, SpecialPeriod sp) {
//		// TODO Auto-generated method stub
//		double currSPP = sp.price();
//		sp.setPrice(newPrice);
//		if (currSPP > newPrice) {
//			// notificá
//			datesSP = this.obsSP.stream().filter(p -> p.equals(sp));
//		}
//	}
//	
	public double price(LocalDate date) {
		// TODO Auto-generated method stub
		return this.pricer.price(date);
	}

	public double priceBetween(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return this.pricer.priceBetween(startDate, endDate);
	}

	@Override
	public void registerPriceObserver(INotifyObserver o, LocalDate startDay, LocalDate endDay) {
		// TODO Auto-generated method stub
//		LocalDate currDay = startDay;
		while (!startDay.isEqual(endDay)) {
			this.registerPriceObserver(o, startDay);
			startDay = startDay.plusDays(1);
		}
		// Caso borde
		this.registerPriceObserver(o, startDay);
	}

	@Override
	public void registerPriceObserver(INotifyObserver o, LocalDate date) {
		// TODO Auto-generated method stub
		Optional<SpecialPeriod> sp = this.pricer.getSPeriods().stream().filter(p -> p.belongs(date)).findFirst();
		if (sp.isPresent()) {
			// Subtarea para agregar a subscribers de Special Periods
			if (this.obsSP.containsKey(date)) {
				this.obsSP.get(date).add(o);
			} else {
				Set<INotifyObserver> os = new HashSet<INotifyObserver>();
				os.add(o);
				this.obsSP.put(date, os);
			}
		} else {
			// Subtarea para agregar a subscribers de Base Price
			if (this.obsBP.containsKey(date)) {
				this.obsBP.get(date).add(o);
			} else {
				Set<INotifyObserver> os = new HashSet<INotifyObserver>();
				os.add(o);
				this.obsBP.put(date, os);
			}
		}
	}
	
	@Override
	public void registerCancelObserver(INotifyObserver o, BookedPeriod bp) {
		// TODO Auto-generated method stub
		// Subtarea para agregar a subscribersCancel
		if (this.obsCancel.containsKey(bp)) {
			this.obsCancel.get(bp).add(o);
		} else {
			Set<INotifyObserver> os = new HashSet<INotifyObserver>();
			os.add(o);
			this.obsCancel.put(bp, os);
		}
	}

	@Override
	public void registerReserveObserver(INotifyObserver o, BookedPeriod bp) {
		// TODO Auto-generated method stub
		if (this.obsReserve.containsKey(bp)) {
			this.obsReserve.get(bp).add(o);
		} else {
			Set<INotifyObserver> os = new HashSet<INotifyObserver>();
			os.add(o);
			this.obsReserve.put(bp, os);
		}
	}

	@Override
	public void notifySubscribersPrice(Booking b, LocalDate date) {
		if (this.obsBP.containsKey(date)) {
			this.obsBP.get(date).stream().forEach(o -> o.update(b, date));
		} else {
			this.obsSP.get(date).stream().forEach(o -> o.update(b, date));
		}
	}
	
	@Override
	public void notifySubscribersReserve(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		if (this.obsReserve.containsKey(bp)) {
			this.obsReserve.get(bp).stream().forEach(o -> o.update(b, bp));
		}
	}

	@Override
	public void notifySubscribersCancelled(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		if (this.obsCancel.containsKey(bp)) {
			this.obsCancel.get(bp).stream().forEach(o -> o.update(b, bp));
		}
	}

	public Property getProperty() {
		// TODO Auto-generated method stub
		return this.property;
	}
	
	public void reserve(Tenant t, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		this.schedule.reserve(t, start, end);
		this.triggerNextRequest();
	}

	public void approveReserve(BookedPeriod bp) { // El Owner aprueba al Tenant solicitado.
		// TODO Auto-generated method stub
		this.state.approveReserve(this, bp);
	}
	
	public void cancelReserve(BookedPeriod bp) {  // BookedPeriod actual
		// TODO Auto-generated method stub
		this.state.cancelReserve(this, bp);
		this.tenant = null; // TODO: Caso borde: había tenant, se cancela y no espera nadie. Qué se hace con esa referencia vieja?
		this.triggerNextRequest();
	}

	public void cancelConditionalReserve(BookedPeriod bp) {
		this.schedule.remove(bp);
		this.notifySubscribersCancelled(this, bp);
		this.applyPolicy(bp);
	}  // BookedPeriod en agenda

	// Private methods
	private boolean hasAHoldingTenant() {
		// TODO Auto-generated method stub
		return this.tenant != null;
	}
	
	private boolean someoneIsWaiting() {
		return !this.schedule.isEmpty();
	}

	// Package methods - (no-modifier) only package path visibility
	void setState(IReserveState state) {
		// TODO Auto-generated method stub
		this.state = state;
	}

	void triggerNextRequest() {
		if (!this.hasAHoldingTenant() && this.someoneIsWaiting()) {
			if (this.schedule.hasReserves(LocalDate.now())) {
				BookedPeriod bp = this.schedule.getNext(LocalDate.now());
				this.state.requestReserve(this, bp);
			}
		}
	}

	public Tenant getTenant() {
		// TODO Auto-generated method stub
		return this.tenant;
	}

	List<PaymentMethod> getPaymentMethods() {
		// TODO Auto-generated method stub
		return this.paymentMethods;
	}

	public void unRegisterPriceObserver(INotifyObserver o, LocalDate date) {
		// TODO Auto-generated method stub
		if (this.obsBP.containsKey(date)) {
			this.obsBP.get(date).remove(o);
		} else {
			this.obsSP.get(date).remove(o);
		}
	}

	public void unRegisterCancelObserver(INotifyObserver o, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.obsCancel.get(bp).remove(o);
	}

	public void unRegisterReserveObserver(INotifyObserver o, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.obsReserve.get(bp).remove(o);
	}

	void setTenant(Tenant t) {
		// TODO Auto-generated method stub
		this.tenant = t;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

}
