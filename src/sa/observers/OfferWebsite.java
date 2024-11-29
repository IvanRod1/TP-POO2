package sa.observers;

import sa.booking.Booking;


public class OfferWebsite implements INotifyObserver{

	private ObjectPublisher publisher;
	@Override
	public void update(Booking b) {
		//PENSARLO BIEN, NECESITO UN PRECIO. SOLO FUNCIONA CON EL BASE PRICE DEL BOOKING
		this.getPublisher().publish("No te pierdas esta oferta: Un inmueble "+ b.getProperty().getPropertyType() +" a tan solo "+ String.valueOf(b.getPricer().getBasePrice()));
	}

	public void setPublisher(ObjectPublisher p) { 
		this.publisher = p;
		
	}
	
	public ObjectPublisher getPublisher() {
		return this.publisher;
	}

}
