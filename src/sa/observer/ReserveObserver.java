package sa.observer;

import sa.booking.Booking;
import sa.observer.interfaces.INotifyObserver;

public class ReserveObserver implements INotifyObserver {

	@Override
	public void update(Booking booking) {
		
		System.out.println("Se alquilo la propiedad:");
		booking.getProperty().summary();
		
	}

}
