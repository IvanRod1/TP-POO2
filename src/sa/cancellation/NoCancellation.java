package sa.cancellation;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.Reserve;

public class NoCancellation implements ICancellationPolicy{

	@Override
	public void activate(Reserve reserve) {
		// TODO Auto-generated method stub
		/*
		 * El metodo activate indica por pantalla la cantidad de dinero que tiene que pagar el inquilino
		 * */
		System.out.println("Se ha procesado la cancelación, tiene que abonar $"+booking.priceBetween(periodCancelled.start(),periodCancelled.end())+" Pesos");
	}

	
	
	

	
}
