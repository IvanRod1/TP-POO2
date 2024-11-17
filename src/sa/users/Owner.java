package sa.users;

import java.util.ArrayList;
import java.util.List;

import sa.accomodationsite.AccomodationSite;
import sa.booking.Booking;
import sa.properties.Property;
import sa.propertyRent.PropertyRent;

public class Owner extends User {
	
	private List<Property> properties;
	
	public Owner(String fullName, int telephone, String mail,AccomodationSite site) {
	
		super(fullName, telephone, mail,site);
		
		this.properties = new ArrayList<Property>();
	}
	

	@Override
	public void summary() {
		// TODO Auto-generated method stub
		/**
		 * aca tambien queda vacío
		 */
		
	}


	public void approveReserve(PropertyRent rent, Booking booking) {
		// TODO Auto-generated method stub
		//if(this.registeredSite.getRents().contains(rent) && rent.getBookings().contains(booking)) {
			booking.getState().approveReserve(booking);
		//}
		
	}


	public void dismissReserve(PropertyRent rent, Booking booking) {
		// TODO Auto-generated method stub
		booking.getState().cancelReserve();
	}


	public void updateNormalPrice(PropertyRent rent, double newPrice) {
		// TODO Auto-generated method stub
		if(this.registeredSite.getRents().contains(rent)) {
			if(newPrice < rent.getPricer().getBasePrice()) {
				rent.getPricer().setBasePrice(newPrice);
				//llamar a observador?
				for(int i=0; i<rent.getBookings().size();i++) {
					rent.getBookings().get(i).getPricer().setBasePrice(rent.priceBetween(rent.getBookings().get(i).getCheckIn(),rent.getBookings().get(i).getCheckOut()));
					rent.getBookings().get(i).updateObservers();
				}
			}
		}
		
	}
	
	

}
