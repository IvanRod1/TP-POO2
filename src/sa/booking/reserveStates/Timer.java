package sa.booking.reserveStates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.observer.BookingSubcriber;

public class Timer implements INotifyTimer {

	private List<BookingSubcriber> bsubscribers;
	
	public Timer() {
		this.bsubscribers = new ArrayList<BookingSubcriber>();
	}

	@Override
	public void register(Booking booking, Reserve reserve, LocalDate date) {
		// TODO Auto-generated method stub
		this.bsubscribers.add(new BookingSubcriber(booking, reserve, date));
		
	}

	@Override
	public void unregister(Booking booking, Reserve reserve, LocalDate date) {
		// TODO Auto-generated method stub
		this.bsubscribers.stream().dropWhile(bs -> bs.getBooking().equals(booking) &&
												   bs.getReserve().equals(reserve) && 
												   bs.getDate().equals(date)          );
	}

	@Override
	public void notify(LocalDate date) {
		// TODO Auto-generated method stub
		this.bsubscribers.stream().filter(bs -> date.equals(bs.getDate()))
								  .forEach(bs -> bs.getBooking().update(bs.getReserve(), date));
	}

	public List<BookingSubcriber> getSubscribers() {
		// TODO Auto-generated method stub
		return this.bsubscribers;
	}

	
}
