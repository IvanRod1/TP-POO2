package sa.users;


import java.util.ArrayList;
import java.util.List;

import sa.booking.Reserve;
import sa.properties.Property;

public class Owner extends User {
	
	private List<Property> 	properties;
	private Reserve reserveRequested;

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
	
	public List<Property> getProperties() {
		return this.properties;
	}
	
	public void reserveRequested(Reserve reserve) {
		this.setReserveRequested(reserve);
		reserve.getTenant().summary();
	}
	
	public void setReserveRequested(Reserve reserve) {
		this.reserveRequested = reserve;
	}
	
	public Reserve getReserveRequested() {
		return this.reserveRequested;
	}
	
	public void cleanRequestedReserve() {
		this.reserveRequested = null;
	}
}
