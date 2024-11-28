package sa.cancellation;

import sa.booking.Reserve;

public interface ICancellationPolicy {

	public void activate(Reserve reserve);
}
