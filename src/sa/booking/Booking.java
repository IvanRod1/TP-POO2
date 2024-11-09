package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.payments.PaymentMethod;
import sa.policies.CostFree;
import sa.policies.ICancellationPolicy;
import sa.properties.Property;
import sa.users.Tenant;

public class Booking {

	private LocalDate				checkIn;
	private LocalDate				checkOut;
	private Property				property;
	private IReserveState			state;
	private ICancellationPolicy		policy;
	private Pricer					pricer;
	private List<PaymentMethod>		paymentMethods;

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
	}

	// Para hacer DOC del state available
	public Booking(ReserveAvailable stateAvailable, CostFree policy, Pricer pricer, Property property, LocalDate checkIn, LocalDate checkOut,
			List<PaymentMethod> paymentMethods, double pricePerDayWeekday, List<Period> periods) {
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

	public void cancelReserve() {
		// TODO Auto-generated method stub
		this.state.cancelReserve();
	}

	public void reserve(Tenant t) {
		// TODO Auto-generated method stub
		this.state.requestReserve(t);		
	}
}
