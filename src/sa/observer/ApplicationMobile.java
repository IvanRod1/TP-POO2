package sa.observer;

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

}
