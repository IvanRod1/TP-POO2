package sa.observer.interfaces;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.Reserve;

public interface INotifyObserver {


	public void updateCancellation(Reserve r);
	public void updateLowerPrice(Booking b);
	public void updateNewReserve(Reserve r);

}
