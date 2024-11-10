package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.payments.PaymentMethod;
import sa.policies.CostFree;
import sa.policies.ICancellationPolicy;
import sa.properties.Property;
import sa.subscriptions.INotifyObserver;
import sa.users.Tenant;
import sa.users.User;

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
	private List<Tenant> 			waitingTenants;

	public Booking(Property property, LocalDate checkIn, LocalDate checkOut, List<PaymentMethod> paymentMethods,
			double pricePerDayWeekday, List<Period> periods) {
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
			List<PaymentMethod> paymentMethods, double pricePerDayWeekday, List<Period> periods, List<INotifyObserver> os, List<Tenant> wT) {
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
		this.policy.activate();
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

	public Property getProperty() {
		// TODO Auto-generated method stub
		return this.property;
	}

	public void reserve(Tenant t) {
		// TODO Auto-generated method stub
		this.waitingTenants.add(t);
		this.triggerNextRequest();
	}

	private void triggerNextRequest() {
		this.tenant = this.waitingTenants.getFirst();
		this.state.requestReserve(this);
	}

	public void approveReserve() { // El Owner aprueba al Tenent solicitado.
		// TODO Auto-generated method stub
		this.state.approveReserve(this);
	}

	public void cancelReserve() {
		// TODO Auto-generated method stub
		this.state.cancelReserve(this);
	}

	protected Tenant getTenant() {
		// TODO Auto-generated method stub
		return this.tenant;
	}

	protected void nextRequest() {
		// TODO Auto-generated method stub
		this.tenant = this.waitingTenants.removeFirst();
		if (!this.waitingTenants.isEmpty()) {
			this.triggerNextRequest();
		}
	}
	
}
