package sa.booking;
import sa.cancellation.*;


import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

//import sa.payments.PaymentMethod;
//import sa.policies.CostFree;
//import sa.policies.ICancellationPolicy;

import sa.properties.Property;
//import sa.subscriptions.INotifyObserver;
import sa.observer.interfaces.INotifyObserver;
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
	private Tenant actualTenant;

	public Booking(Property property, LocalDate checkIn, LocalDate checkOut, List<PaymentMethod> paymentMethods,
			double pricePerDayWeekday, List<Period> periods) {
		// TODO Auto-generated constructor stub
		this.state 				= (IReserveState) new ReserveAvailable();
		this.pricer		 		= new Pricer(pricePerDayWeekday, periods);
		this.policy				= (ICancellationPolicy) new CostFree();
		this.paymentMethods		= paymentMethods;
		this.checkIn			= checkIn;
		this.checkOut			= checkOut;
		this.property			= property;
		this.subscribers 		= new ArrayList<INotifyObserver>();
	}

	// Para hacer DOC del state available
	public Booking(ReserveAvailable stateAvailable, CostFree policy, Pricer pricer, Property property, LocalDate checkIn, LocalDate checkOut,
			List<PaymentMethod> paymentMethods, double pricePerDayWeekday, List<Period> periods, List<INotifyObserver> os) {
		// TODO Auto-generated constructor stub
		this.state 			= (IReserveState) stateAvailable;
		this.pricer 		= pricer;
		this.pricer.setBasePrice(pricePerDayWeekday);
		periods.stream().forEach(p -> this.pricer.addSpecialPeriod(p));
		this.policy			= policy;
		this.paymentMethods	= paymentMethods;
		this.checkIn		= checkIn;
		this.checkOut		= checkOut;
		this.property		= property;
		this.subscribers 	= os;
		this.actualTenant = null;
	}


	public IReserveState getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	protected void setState(IReserveState state) {
		// TODO Auto-generated method stub
		this.state = state;
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
		this.policy.activate(this);
	}

	public double price(LocalDate date) {
		// TODO Auto-generated method stub
		return this.pricer.price(date);
	}

	public double priceBetween(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return this.pricer.priceBetween(startDate, endDate);
	}

	public void cancelReserve() {
		// TODO Auto-generated method stub
		this.state.cancelReserve();
	}

	public void reserve(Tenant t) {
		// TODO Auto-generated method stub
		this.state.requestReserve(t);		
	}

	@Override
	public void registerObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		this.subscribers.add(o);
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

	public LocalDate getCheckIn() {
		//Lo agrego Ivan
		return this.checkIn;
	}

	public LocalDate getCheckOut() {
		//Lo agrego Ivan
		return this.checkOut;
	}

	public String getCity() {
		//Lo agrego Ivan
		return this.property.getCity();
	}

	public int getMaxGuest() {
		//Lo agrego Ivan
		return this.property.getMaxGuests();
	}

	public Property getProperty() {
		//Lo agrego Ivan
		return this.property;
	}

	public Tenant getTenant() {
		//Lo agrego Ivan
		return this.actualTenant;
	}

}
