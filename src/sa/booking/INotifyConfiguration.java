package sa.booking;

import java.time.LocalDate;

import sa.subscriptions.INotifyObserver;

public interface INotifyConfiguration {
	
	public void registerPriceObserver(INotifyObserver o, LocalDate startDay, LocalDate endDay);
	public void registerPriceObserver(INotifyObserver o, LocalDate date);
	public void registerCancelObserver(INotifyObserver o, BookedPeriod bp);
	public void registerReserveObserver(INotifyObserver o, BookedPeriod bp);

	public void notifySubscribersPrice(Booking b, LocalDate date);
	public void notifySubscribersReserve(Booking b, BookedPeriod bp);
	public void notifySubscribersCancelled(Booking b, BookedPeriod bp);
}
