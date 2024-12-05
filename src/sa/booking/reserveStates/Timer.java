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
import sa.subscriptions.INotifyTimerSubscriber;

public class Timer implements INotifyTimer {

	private HashMap<LocalDate, Set<INotifyTimerSubscriber>> rsubscribers;
	
	public Timer() {
		this.rsubscribers = new HashMap<LocalDate, Set<INotifyTimerSubscriber>>();
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
	public void register(INotifyTimerSubscriber subscriber, LocalDate date) {
		// TODO Auto-generated method stub
		if (this.rsubscribers.containsKey(date)) {
			this.rsubscribers.get(date).add(subscriber);
		} else {
			HashSet<INotifyTimerSubscriber> rs = new HashSet<INotifyTimerSubscriber>();
			rs.add(subscriber);
			this.rsubscribers.put(date, rs);
		}		
	}

	@Override
	public void unregister(INotifyTimerSubscriber subscriber, LocalDate date) {
		// TODO Auto-generated method stub
//		this.rsubscribers.stream().dropWhile(bs -> bs.getBooking().equals(booking) &&
//												   bs.getReserve().equals(reserve) && 
//												   bs.getDate().equals(date)          );
		if (this.rsubscribers.containsKey(date)) {
			this.rsubscribers.get(date).remove(subscriber);
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

	public List<INotifyTimerSubscriber> getSubscribers() {
		// TODO Auto-generated method stub
		return this.rsubscribers.keySet().stream(). // FIXME: tomar los sets de cada key
	}

	
}
