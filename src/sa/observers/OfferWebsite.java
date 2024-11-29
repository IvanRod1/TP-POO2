package sa.observers;

import sa.booking.Booking;

public class OfferWebsite implements INotifyObserver{

	private ObjectPublisher publisher;
	@Override
	public void update(Booking b) {
		//PENSARLO BIEN, NECESITO UN PRECIO
		this.getPublisher().publish("No te pierdas esta oferta: Un inmueble "+ b.getProperty().getPropertyType());
	}

	public void setPublisher(ObjectPublisher p) {
		this.publisher = p;
		
	}
	
	public ObjectPublisher getPublisher() {
		return this.publisher;
	}

}
