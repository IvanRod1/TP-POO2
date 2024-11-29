package sa.cancellation;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.Reserve;

public interface ICancellationPolicy {
//	public void activate(LocalDate cancellationDate,Booking booking, BookedPeriod periodCancelled);

	public void activate(Reserve reserve); // La fecha de cancelación es la actual al momento en que se genera la comunicación y la 'reserve' conoce al Booking al que pertenece.

}
