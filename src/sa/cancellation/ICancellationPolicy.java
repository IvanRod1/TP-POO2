package sa.cancellation;

import sa.booking.Booking;

public interface ICancellationPolicy {
	public void activate(Booking booking);

}
