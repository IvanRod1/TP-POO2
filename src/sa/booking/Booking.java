package sa.booking;

import java.time.LocalDate;
import java.util.List;

import sa.payments.PaymentMethod;
import sa.policies.ICancellationPolicy;
import sa.properties.Property;

public class Booking {

	private IReserveState	state;
	private ICancellationPolicy	policy;

	public Booking(Property property, LocalDate checkIn, LocalDate checkOut, List<PaymentMethod> paymentMethods,
			double pricePerDayWeekday, double pricePerDayHighSeason, double pricePerDayLongWeekend) {
		// TODO Auto-generated constructor stub
		this.state = (IReserveState) new ReserveAvailable();
	}

	// Para hacer DOC del state available
	public Booking(ReserveAvailable stateAvailable, Property property, LocalDate checkIn, LocalDate checkOut,
			List<PaymentMethod> paymentMethods, double pricePerDayWeekday, double pricePerDayHighSeason,
			double pricePerDayLongWeekend) {
		// TODO Auto-generated constructor stub
		this.state = (IReserveState) stateAvailable;
		
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

}
