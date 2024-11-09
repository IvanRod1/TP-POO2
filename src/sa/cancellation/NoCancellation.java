package sa.cancellation;

import sa.booking.Booking;

public class NoCancellation implements ICancellationPolicy{

	@Override
	public void activate(Booking booking) {
		// TODO Auto-generated method stub
		System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.price(booking.getCheckIn())+" Pesos");
		
	}

	
}
