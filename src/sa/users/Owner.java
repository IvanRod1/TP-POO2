package sa.users;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

public class Owner extends User {

	private Booking	currentRequest;

	public void reserveRequestedOn(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.currentRequest = b;
		// Acá el owner puede visualizar datos del tenant
		bp.tenant().summary();
	}

	@Override
	public void reserveCancelled(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		
	}
}
