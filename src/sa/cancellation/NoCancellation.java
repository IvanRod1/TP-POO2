package sa.cancellation;

import java.time.LocalDate;

import sa.booking.Booking;

public class NoCancellation implements ICancellationPolicy{

	@Override
	public void activate(LocalDate date,Booking booking) {
		// TODO Auto-generated method stub
		//System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.price(booking.getCheckIn())+" Pesos");
		System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.priceBetween(booking.getCheckIn(),booking.getCheckOut())+" Pesos");

	}

	
}
