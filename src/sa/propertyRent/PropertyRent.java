package sa.propertyRent;

import java.time.LocalDate;
import java.util.List;

import sa.booking.Booking;
import sa.booking.Pricer;
import sa.users.Tenant;

public class PropertyRent {

	private List<Booking> bookings;
	private Pricer pricer;
	
	
	public void cancelReserve(Tenant tenant) {
		// TODO Auto-generated method stub
		
	}

	public List<Booking> getBookings() {
		// TODO Auto-generated method stub
		return this.bookings;
	}

	public Pricer getPricer() {
		// TODO Auto-generated method stub
		return this.pricer;
	}

	public void updatePricesBookings() {
		// TODO Auto-generated method stub
		
	}

	public double priceBetween(LocalDate checkIn, LocalDate checkOut) {
		LocalDate current = checkIn;
		double total = 0;
		while(current != checkOut) {
			total+= this.pricer.getPrice(current);
			current = current.plusDays(1);
		}
		return total;
	}

}
