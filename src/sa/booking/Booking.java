package sa.booking;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sa.payments.PaymentMethod;
import sa.policies.CostFree;
import sa.policies.ICancellationPolicy;
import sa.properties.Property;
import sa.subscriptions.INotifyObserver;
import sa.users.Tenant;


public class Booking implements INotifyConfiguration {

	private LocalDate				checkIn;
	private LocalDate				checkOut;
	private Property				property;
	private IReserveState			state;
	private ICancellationPolicy		policy;
	private Pricer					pricer;
	private List<PaymentMethod>		paymentMethods;
	private List<INotifyObserver>	subscribers;
	private Tenant 					tenant;
	
	// queue1 INV igual en largo que la queue2
	private List<Tenant> 			waitingTenants; // queue1
	private List<Period>			bookedPeriods;	// queue2

	public Booking(Property property, LocalDate checkIn, LocalDate checkOut, List<PaymentMethod> paymentMethods,
			double pricePerDayWeekday, List<SpecialPeriod> periods) {
		// TODO Auto-generated constructor stub
		this.state 				= new ReserveAvailable();
		this.pricer		 		= new Pricer(pricePerDayWeekday, periods);
		this.policy				= (ICancellationPolicy) new CostFree();
		this.paymentMethods		= paymentMethods;
		this.checkIn			= checkIn;
		this.checkOut			= checkOut;
		this.property			= property;
		this.subscribers 		= new ArrayList<INotifyObserver>();
		this.waitingTenants		= new ArrayList<Tenant>();
	}

	// Para hacer DOC del state available
	public Booking(ReserveAvailable stateAvailable, CostFree policy, Pricer pricer, Property property, LocalDate checkIn, LocalDate checkOut,
			List<PaymentMethod> paymentMethods, double pricePerDayWeekday, List<SpecialPeriod> periods, List<INotifyObserver> os, List<Tenant> wT) {
		// TODO Auto-generated constructor stub
		this.state 			= stateAvailable;
		this.pricer 		= pricer;
		this.pricer.setBasePrice(pricePerDayWeekday);
		periods.stream().forEach(p -> this.pricer.addSpecialPeriod(p));
		this.policy			= policy;
		this.paymentMethods	= paymentMethods;
		this.checkIn		= checkIn;
		this.checkOut		= checkOut;
		this.property		= property;
		this.subscribers 	= os;
		this.waitingTenants	= wT;
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

	public void applyPolicy() {
		// TODO Auto-generated method stub
		this.policy.activate();
	}

	public void setBasePrice(double newPrice) {
		// TODO Auto-generated method stub
		this.pricer.setBasePrice(newPrice);
	}

	public void setBasePrice(double newPrice) {
		// TODO Auto-generated method stub
		double currBP = this.pricer.getBasePrice();
		this.pricer.setBasePrice(newPrice);
		if (currBP > newPrice) {
			// notificá
			this.subscribersBP.values().stream().forEach(o -> o.nofity);
		} 		
	}

	public void setSPPrice(double newPrice, SpecialPeriod sp) {
		// TODO Auto-generated method stub
		double currSPP = this.pricer.getSPPrice(sp);
		this.pricer.setSPPrice(newPrice);
		if (currSPP > newPrice) {
			// notificá
			datesSP = this.subscribersSP.stream().filter(p -> p.equals(sp));
		}
	}
	
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
		// Modelar como diccioario
		currDay = startDay;
		while (currDate != endDay) {
			// Subtarea
			registerPriceObserver(o, currDay)
			currDay.plusDay(1);
		}
		// Subtarea por caso borde
		registerPriceObserver(o, currDay)
	}

	// SpecialPeriod bajó valor (eran 5 días)
	// realizar set de observers (sin repetidos) y aviso
	
	@Override
	public void registerPriceObserver(INotifyObserver o, LocalDate date) {
		// TODO Auto-generated method stub
		// Modelar como diccioario
		Optional<SpecialPeriod> sp = this.pricer.periods().stream().findFirst( p -> p.belongs(date));
		if sp.isPresent() {
			// Subtarea para agregar a subscribersSP
			if (this.subscribersSP.exist(date)) {
				this.subscribersSP.valores().add(o)
			} else {
				this.subscribersSP.add(date: o);
			}
		} else {
			// Subtarea para agregar a subscribersBP
			if (this.subscribersBP.exist(date)) {
				this.subscribersBP.valores().add(o)
			} else {
				this.subscribersBP.add(date: o);
			}
		}
	}
	
	@Override
	public void registerCancelObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.subscribersCancel.add(o);
	}


	@Override
	public void registerReserveObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.subscribers3.add(o);
	}

	@Override
	public void nofifySubscribersReserve(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.subscribersReserve();
	}

	@Override
	public void nofifySubscribersCancelled(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.subscribersCancel();
	}
	
	
	@Override
	public void unregisterObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.subscribers.remove(o);
	}

	@Override
	public void updateObservers() {
		// TODO Auto-generated method stub
		this.subscribers.stream().forEach(o -> o.update(this));
	}

	public Property getProperty() {
		// TODO Auto-generated method stub
		return this.property;
	}

	BookedPeriod()
	LocalDate 		start;
	LocalDate 		end;
	Tenant			t;
	public LocalDate start() {return this.start();}
	public LocalDate end() {return this.end();}
	public LocalDate tenant() {return this.tenant();}
	public boolean belongs(LocalDate d) {return checkea d;}
	
	
	public void reserve(Tenant t, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
//		Optional<BookedPeriod> p = this.bookedPeriods.stream().findFirst(bp -> bp.belongs(start));
//		if (!p.isPresent()) {
			BookedPeriod bp = new BookedPeriod(t, start, end);
			if (this.bookedPeriods.exist(start)) {
				this.bookedPeriods.valores().add(bp) // Lista orden de llegada
			} else {
				this.bookedPeriods.add(start: [bp]);
			}
//			this.waitingTenants.add(t);
			this.triggerNextRequest();
		}
//		} else {
//			throw new Exception("No te puedo tomar esa reserva porque alguien ya reservó.");
//		}
	}
	
	public void approveReserve(BookedPeriod bp) { // El Owner aprueba al Tenant solicitado.
		// TODO Auto-generated method stub
		this.state.approveReserve(this, bp);
	}
	
	public void cancelReserve(BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.state.cancelReserve(this, bp);
		this.tenant = null; // TODO: Caso borde: había tenant, se cancela y no espera nadie. Qué se hace con esa referencia vieja?
		this.triggerNextRequest();
	}

	// Private methods
	private boolean hasAHoldingTenant() {
		// TODO Auto-generated method stub
		return this.tenant != null;
	}
	
	private boolean someoneIsWaiting() {
		return !this.waitingTenants.isEmpty();
	}

	// Package methods - (no-modifier) only package path visibility
	void setState(IReserveState state) {
		// TODO Auto-generated method stub
		this.state = state;
	}

	void triggerNextRequest() {
		if (!this.hasAHoldingTenant() && this.someoneIsWaiting()) {
			if (this.bookedPeriods.has(LocalDate.now())) {
				BookedPeriod bp = this.bookedPeriods.get(LocalDate.now()).removeFirst();
//				this.tenant = this.waitingTenants.removeFirst();
//				Period p	= this.bookedPeriods.removeFirst();
				this.state.requestReserve(this, bp);
			}
		}
	}

	Tenant getTenant() {
		// TODO Auto-generated method stub
		return this.tenant;
	}

	List<PaymentMethod> getPaymentMethods() {
		// TODO Auto-generated method stub
		return this.paymentMethods;
	}
	
}
