package sa.booking.reserveStates;

import java.time.LocalDate;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.subscriptions.INotifyTimerSubscriber;

public interface INotifyTimer {

	public void register(INotifyTimerSubscriber subscriber, LocalDate date);
	public void unregister(INotifyTimerSubscriber subscriber, LocalDate date);
	public void notify(LocalDate date);
	
}
