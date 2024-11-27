package sa.booking.reserveStates;

import java.time.LocalDate;

import sa.booking.Booking;
import sa.booking.Reserve;

public interface INotifyTimer {

	public void register(Booking booking, Reserve reserve, LocalDate date);
}
