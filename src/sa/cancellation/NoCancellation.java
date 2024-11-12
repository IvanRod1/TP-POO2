package sa.cancellation;

import java.time.LocalDate;

import sa.booking.Booking;

public class NoCancellation implements ICancellationPolicy{

	@Override
	public void activate(LocalDate date,Booking booking) {

		/*
		 * El metodo activate indica por pantalla la cantidad de dinero que tiene que pagar el inquilino
		 * */
		//System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.price(booking.getCheckIn())+" Pesos");
		System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.priceBetween(booking.getCheckIn(),booking.getCheckOut())+" Pesos");

	}

	
}
