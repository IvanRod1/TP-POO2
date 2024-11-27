package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sa.booking.reserveStates.IReserveState;
import sa.booking.reserveStates.ReserveBooked;
import sa.cancellation.CostFree;
import sa.cancellation.ICancellationPolicy;
import sa.properties.Property;
import sa.subscriptions.INotifyTimerSubscriber;
import sa.users.Tenant;
import sa.observer.interfaces.INotifyObserver;



public class Booking implements INotifyConfiguration, INotifyTimerSubscriber {

	private Period					period;
	private Property				property;
	private Pricer					pricer;

	private IReserveState			state;
	private ICancellationPolicy		policy;

	private List<PaymentMethod>			paymentMethods;
	private List<Reserve>				reserves;
	private List<Reserve>				waitings;
	private Timer						timer;

	private List<INotifyObserver> 	obsCancel;
	private List<INotifyObserver> 	obsReserve;
	private List<INotifyObserver> 	obsPrice;


	public Booking(Property property, LocalDate begin, LocalDate end, List<PaymentMethod> paymentMethods,
			double pricePerDayWeekday, List<SpecialPeriod> periods) {
		// TODO Auto-generated constructor stub
		this.pricer		 		= new Pricer(pricePerDayWeekday, periods);
		this.policy				= new CostFree();
		this.paymentMethods		= paymentMethods;
		this.setPeriod(new Period(begin, end));
		this.property			= property;
		this.obsPrice			= new ArrayList<INotifyObserver>();
		this.obsCancel  		= new ArrayList<INotifyObserver>();
		this.obsReserve  		= new ArrayList<INotifyObserver>();
	}

	// Para hacer DOC de sus atributos.
	public Booking(CostFree policy, Pricer pricer, Property property,
			List<PaymentMethod> paymentMethods, double pricePerDayWeekday, List<SpecialPeriod> specialPeriods,
			Period period, List<Reserve> reserves, List<Reserve> waitings, Timer timer, List<INotifyObserver> obsCancel
			, List<INotifyObserver> obsReserve, List<INotifyObserver> obsPrice) {
		// TODO Auto-generated constructor stub
		this.pricer 		= pricer;
		this.pricer.setBasePrice(pricePerDayWeekday);
		specialPeriods.stream().forEach(p -> this.pricer.addSpecialPeriod(p));
		this.policy			= policy;
		this.paymentMethods	= paymentMethods;
		this.property		= property;
		this.period			= period;
		this.reserves		= reserves;
		this.waitings		= waitings;
		this.timer			= timer;
		this.obsCancel		= obsCancel;
		this.obsReserve		= obsReserve;
		this.obsPrice		= obsPrice;
	}

	public void newReserve(Tenant t, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
//		this.schedule.reserve(t, start, end);
		this.getProperty().getOwner().reserveRequestedOn(new Reserve(this, t, new Period(start, end)));
//		this.triggerNextRequest();
	}

	public void newConditionalReserve(Tenant t, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		this.waitings.add(new Reserve(this, t, new Period(start, end)));
		
	}

	public Property getProperty() {
		// TODO Auto-generated method stub
		return this.property;
	}

	public List<PaymentMethod> getPaymentMethods() {
		// TODO Auto-generated method stub
		return this.paymentMethods;
	}

	public void setCancellationPolicy(ICancellationPolicy policy) {
		// TODO Auto-generated method stub
		this.policy = policy;
	}

	public ICancellationPolicy getPolicy() {
		// TODO Auto-generated method stub
		return this.policy;
	}

	public void applyPolicy(Reserve r) {
		// TODO Auto-generated method stub
		this.policy.activate(r);
	}

	public void setBasePrice(double newPrice) {
		// TODO Auto-generated method stub
		double currBP = this.pricer.getBasePrice();
		this.pricer.setBasePrice(newPrice);
		if (currBP > newPrice) {
			// notificá que el booking bajó de precio base
			this.notifySubscribersPrice();
		} 		
	}

	public void setSPPrice(double newPrice, SpecialPeriod sp) {
		// TODO Auto-generated method stub
		double currSPP = sp.price();
		sp.setPrice(newPrice);
		if (currSPP > newPrice) {
			// notificá que el booking bajó de precio
			this.notifySubscribersPrice();
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
	public void registerPriceObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.obsPrice.add(o);
	}

	@Override
	public void registerCancelObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.obsCancel.add(o);
	}

	@Override
	public void registerReserveObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.obsReserve.add(o);
	}

	@Override
	public void notifySubscribersReserve(Reserve r) {
		// TODO Auto-generated method stub
		this.obsReserve.stream().forEach(o -> o.updateNewReserve(r));
	}

	@Override
	public void notifySubscribersPrice() {
		// TODO Auto-generated method stub
		this.obsPrice.stream().forEach(o -> o.updateLowerPrice(this));
	}

	@Override
	public void notifySubscribersCancelled(Reserve r) {
		// TODO Auto-generated method stub
		this.obsCancel.stream().forEach(o -> o.updateCancellation(r));
	}

	public void unRegisterPriceObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.obsPrice.remove(o);
	}

	public void unRegisterCancelObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.obsCancel.remove(o);
	}

	public void unRegisterReserveObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.obsReserve.remove(o);
	}

	public Period getPeriod() {
		return this.period;
	}

	private void setPeriod(Period p) {
		this.period = p;
	}

	public List<Reserve> getReserves() {
		// TODO Auto-generated method stub
		return this.reserves;
	}

	public List<Reserve> getConditionalReserves() {
		// TODO Auto-generated method stub
		return this.waitings;
	}

	void addReserve(Reserve reserve) {
		// TODO Auto-generated method stub
		// TODO: debería cobrarle al Tenant porque ya fue aceptada
		this.getReserves().add(reserve);
//		reserve.registerToTimer(timer, reserve.getCheckIn());
		this.getTimer().register(this, reserve, reserve.getCheckIn());
	}

	private Timer getTimer() {
		// TODO Auto-generated method stub
		return this.timer;
	}

	@Override
	public void update(Reserve r, LocalDate date) {
		// TODO Auto-generated method stub
		this.getTimer().unregister(this, r, date);
		if (date.equals(r.getCheckOut())) {
			// Se terminó la ocupación
			// Hay que remover esta reserve de la lista 'reserves' pero antes preguntar si no hay nadie esperando
			this.getReserves().remove(r);
			this.triggerNextRequest(date);
		} else { // Comienza su tiempo de ocupación
			r.next();
			this.getTimer().register(this, r, r.getCheckOut());
		}
	}

	void triggerNextRequest(LocalDate date) {
		Optional<Reserve> wr = this.getConditionalReserves().stream()
								.filter(w -> date.equals(w.getCheckIn()))
								.findFirst();
		// Si está en waiting pasa a ser una reserva formal
		if (wr.isPresent()) {
			Reserve next_r = wr.get();
			this.getConditionalReserves().remove(next_r);
			this.getProperty().getOwner().reserveRequestedOn(next_r);
		}
	}
}
