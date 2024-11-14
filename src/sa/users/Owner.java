package sa.users;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

import java.util.ArrayList;
import java.util.List;

import sa.properties.Property;

public class Owner extends User {
	
	private List<Property> properties;
	private Booking	currentRequest = null;
	
	public Owner(String fullName, int telephone, String mail) {
	
		super(fullName, telephone, mail);
		
		this.properties = new ArrayList<Property>();
	}
	

	@Override
	public void summary() {
		// TODO Auto-generated method stub
		/**
		 * aca tambien queda vacío
		 */
		
	}

	@Override
	public void reserveCancelled(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		
	}

	public void reserveRequestedOn(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.currentRequest = b;
		// Acá el owner puede visualizar datos del tenant
		bp.tenant().summary();
	}

}
