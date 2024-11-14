package sa.cancellation;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

public interface ICancellationPolicy {
	public void activate(LocalDate cancellationDate,Booking booking, BookedPeriod periodCancelled);

}
