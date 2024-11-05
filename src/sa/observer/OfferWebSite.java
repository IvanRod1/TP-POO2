package sa.observer;

import sa.booking.Booking;
import sa.observer.interfaces.INotifyObserver;

public class OfferWebSite implements INotifyObserver{

	private ObjectPublisher publisher;
	@Override
	public void update(Booking booking) {
		// TODO Auto-generated method stub
		System.out.println("Le paso el booking al objeto colaborativo");
	}

}
