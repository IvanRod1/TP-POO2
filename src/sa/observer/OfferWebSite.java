package sa.observer;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.observer.interfaces.INotifyObserver;

public class OfferWebSite implements INotifyObserver{

	private ObjectPublisher publisher;
	@Override
	public void update(Booking booking) {
		// TODO Auto-generated method stub
		System.out.println("Le paso el booking al objeto colaborativo");
	}

	@Override
	public void update(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		System.out.println("Le paso el booking al objeto colaborativo y el bookedperiod en cuestion");
	}
	@Override
	public void update(Booking b, LocalDate date) {
		// TODO Auto-generated method stub
		System.out.println("Le paso el booking al objeto colaborativo y el date en cuestion");
	}

}
