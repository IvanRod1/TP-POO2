package sa.subscriptions;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

public interface INotifyObserver {


	public void update(Booking b);

	public void update(Booking b, BookedPeriod bp);

	public void update(Booking b, LocalDate date);

}
