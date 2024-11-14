package sa.cancellation;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

public class NoCancellation implements ICancellationPolicy{

	@Override
	public void activate(LocalDate cancellationDate, Booking booking, BookedPeriod periodCancelled) {
		/*
		 * El metodo activate indica por pantalla la cantidad de dinero que tiene que pagar el inquilino
		 * */
		System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.priceBetween(periodCancelled.getCheckIn(),periodCancelled.getCheckOut())+" Pesos");
		
	}

	
	
	

	
}
