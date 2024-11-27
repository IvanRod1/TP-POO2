package sa.users;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.Period;
import sa.booking.Reserve;

import java.util.ArrayList;
import java.util.List;

import sa.properties.Property;

public class Owner extends User {
	
	private List<Property> 	properties;
	private Reserve  		requestedReserve = null;

	public Owner() {
		
	}

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

	public void reserveRequestedOn(Reserve r) {
		// TODO Auto-generated method stub
		this.requestedReserve  = r;
		// Acá el owner puede visualizar datos del tenant
		r.getTenant().summary();
	}
	
	public Reserve getRequestedReserve() {
		return this.requestedReserve;
	}
}
