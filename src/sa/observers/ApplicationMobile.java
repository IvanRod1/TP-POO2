package sa.observers;

import sa.booking.Booking;


public class ApplicationMobile implements INotifyObserver{

	private ObjectScreen screen;
	
	@Override
	public void update(Booking b) {
		
		getScreen().popUp(b.getProperty().getPropertyType().type(), "Rojo", 3);
		
	}
	
	public ObjectScreen getScreen() {
		return this.screen;
	}

	public void setScreen(ObjectScreen s) {
		this.screen = s;
	}

}
