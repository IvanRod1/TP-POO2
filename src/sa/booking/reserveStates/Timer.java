package sa.booking.reserveStates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.observer.BookingSubscriber;

public class Timer implements INotifyTimer {

	private HashMap<LocalDate, Set<Reserve>> rsubscribers;
	private List<BookingSubscriber> bsubscribers;
	
	public Timer() {
		this.rsubscribers = new HashMap<LocalDate, Set<Reserve>>();
		this.bsubscribers = new ArrayList<BookingSubscriber>();
		this.tick();
	}

	private void tick() {
		// TODO Auto-generated method stub
		LocalDate currDate = LocalDate.now(); 
		while (true) {
			this.notify(currDate);
			try { wait(8492400); }
			catch (InterruptedException e) { e.printStackTrace();}
			currDate = LocalDate.now();
		}
	}

	@Override
	public void register(Booking booking, Reserve reserve, LocalDate date) {
		// TODO Auto-generated method stub
//		this.bsubscribers.add(new BookingSubscriber(booking, reserve, date));
		if (this.rsubscribers.containsKey(date)) {
			this.rsubscribers.get(date).add(reserve);
		} else {
			HashSet<Reserve> rs = new HashSet<Reserve>();
			rs.add(reserve);
			this.rsubscribers.put(date, rs);
		}		
	}

	@Override
	public void unregister(Booking booking, Reserve reserve, LocalDate date) {
		// TODO Auto-generated method stub
//		this.rsubscribers.stream().dropWhile(bs -> bs.getBooking().equals(booking) &&
//												   bs.getReserve().equals(reserve) && 
//												   bs.getDate().equals(date)          );
		if (this.rsubscribers.containsKey(date)) {
			this.rsubscribers.get(date).remove(reserve);
		}
	}

	@Override
	public void notify(LocalDate date) {
		// TODO Auto-generated method stub
//		this.rsubscribers.stream().filter(bs -> date.equals(bs.getDate()))
//								  .forEach(bs -> bs.getBooking().update(bs.getReserve(), date));
		this.rsubscribers.get(date).stream().filter(r -> date.equals(r.getCheckIn()) || date.equals(r.getCheckOut()))
											.forEach(r -> r.getBooking().update(r, date));
	}

	public List<BookingSubscriber> getSubscribers() {
		// TODO Auto-generated method stub
		return this.bsubscribers;
	}

	
}
