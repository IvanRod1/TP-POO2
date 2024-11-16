package sa.accomodationsite;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sa.paymentMethod.PaymentMethod;
import sa.properties.Property;
import sa.propertyRent.PropertyRent;
import sa.users.Owner;
import sa.users.Tenant;

public class AccomodationSite {
	
	private List<Tenant> tenants;
	private List<Owner> owners;
	private IQuery searcher;
	
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

	
		
}
	
 	
	
	

