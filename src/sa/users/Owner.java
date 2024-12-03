package sa.users;

import sa.booking.Reserve;

import java.util.ArrayList;
import java.util.List;

import sa.properties.Property;

public class Owner extends User {
	
	private List<Property> 	properties;
	private Reserve  		requestedReserve = null;

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


	public void reserveRequested(Reserve r) {
		// TODO Auto-generated method stub
		this.requestedReserve  = r;
		// Acá el owner puede visualizar datos del tenant
		r.getTenant().summary();
	}
	
	public Reserve getRequestedReserve() {
		return this.requestedReserve;
	}
}
