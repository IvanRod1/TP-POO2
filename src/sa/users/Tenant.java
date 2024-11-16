package sa.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sa.accomodationsite.AccomodationSite;
import sa.booking.Booking;
import sa.paymentMethod.PaymentMethod;
import sa.propertyRent.PropertyRent;


public class Tenant extends User {
	
	public Tenant(String fullName, int telephone, String mail,AccomodationSite site) {
		
		super(fullName, telephone, mail,site);
	}

	@Override
	public void summary() {
		// TODO Auto-generated method stub
		/**
		 * 
		 * * hacer lo mismo que en property 
		 */
		System.out.println("Property Summary:");
	    System.out.println("Area: " + this.fullName);
	    System.out.println("Area: " + this.telephone);
	    System.out.println("Area: " + this.mail);
		
	}

	public void makeReserve(PropertyRent aviso, LocalDate checkIn, LocalDate checkOut, PaymentMethod method) {
		// TODO Auto-generated method stub
		if(this.registeredSite.verifyBookingAttempt(aviso,checkIn,checkOut,method)) {
			this.registeredSite.addBooking(checkIn,checkOut,this,aviso);
		}
		else {
			System.out.println("No se pudo hacer la reserva, No esta disponible");
		}
		
	}

	public void cancelReserve(PropertyRent rent) {
		this.registeredSite.getPropertyRent(rent).cancelReserve(this);
		
	}

	public List<Booking> futureBookings() {
		List<Booking> bookingsTenant = this.allBookings();
		List<Booking> futureBookings = new ArrayList<Booking>();
		
		for(int i=0;i<bookingsTenant.size();i++) {
			if(bookingsTenant.get(i).getCheckIn().isAfter(this.registeredSite.getCurrentDate())) {
				futureBookings.add(bookingsTenant.get(i));
			}
		}
		return futureBookings;
	}

	public List<Booking> allBookings() {
		// TODO Auto-generated method stub
		return this.registeredSite.getBookingsOf(this);
	}

	public Set<String> allBookingsCities() {
		List<Booking> bookingsTenant = this.allBookings();
		Set<String> cities = new HashSet<String>();
		for(int i=0;i<bookingsTenant.size();i++) {
			cities.add(bookingsTenant.get(i).getProperty().getCity());
		}
		return cities;
	}
	

}
