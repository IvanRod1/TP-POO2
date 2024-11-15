package sa.observer;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.observer.interfaces.INotifyObserver;

public class ApplicationMobile implements INotifyObserver{

	private ObjectScreen screen;

	@Override
	public void update(Booking booking) {
		// TODO Auto-generated method stub
		if(booking.getState().equals("Available")) {
			System.out.println("Se mando al objeto que publica las notificaciones el booking");
		}
		else {
			System.out.println("No mando nada");
		}
		
	}
	
	public ObjectScreen getScreen() {
		return this.screen;
	}
	
	public void setScreen(ObjectScreen screen) {
		this.screen = screen;
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
