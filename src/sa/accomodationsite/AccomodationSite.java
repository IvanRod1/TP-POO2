package sa.accomodationsite;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sa.booking.Booking;
import sa.paymentMethod.PaymentMethod;
import sa.properties.Property;
import sa.propertyRent.PropertyRent;
import sa.users.Owner;
import sa.users.Tenant;
import sa.users.TenantTest;

public class AccomodationSite {
	
	private List<Tenant> tenants;
	private List<Owner> owners;
	private IQuery searcher;
	private List<Booking> bookings;
	private List<PropertyRent> rents;
	private Administrator administrator;
	
	
	public AccomodationSite() {
		this.tenants = new ArrayList<Tenant>();
		this.owners = new ArrayList<Owner>();
	}
	
	public boolean verifyBookingAttempt(PropertyRent aviso, LocalDate checkIn, LocalDate checkOut,
			PaymentMethod method) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addBooking(LocalDate checkIn, LocalDate checkOut, Tenant tenant, PropertyRent aviso) {
		// TODO Auto-generated method stub
		
	}

	public PropertyRent getPropertyRent(PropertyRent rent) {

		for(int i=0;i<this.rents.size();i++) {
			if(this.rents.get(i).equals(rent)) {
				return this.rents.get(i);
			}
		}
	}

	public List<Booking> getBookingsOf(Tenant tenant) {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalDate getCurrentDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PropertyRent> getRents() {
		// TODO Auto-generated method stub
		return this.rents;
	}

	
		
}
	
 	
	
	

