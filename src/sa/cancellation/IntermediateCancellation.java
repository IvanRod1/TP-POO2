package sa.cancellation;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

public class IntermediateCancellation implements ICancellationPolicy{

	

	@Override
	public void activate(LocalDate cancellationDate, Booking booking, BookedPeriod periodCancelled) {
		/*
		 * Este metodo se encarga de verificar la fecha de cancelacion del booking con la fecha de activacion de la politica
		 * Si la fecha de cancelacion es 20 dias previa, no se paga nada y se lo notifica en pantalla
		 * Si es 10 dias previa, se paga la mitad del precio
		 * Y si no es ninguna de las 2, se paga todo 
		 * */
		
		if(cancellationDate.plusDays(20).isBefore(periodCancelled.start())) {
			System.out.println("No pagas nada");
		}else if (cancellationDate.plusDays(10).isBefore(periodCancelled.start())) {
			System.out.println("Se paga la mitad "+booking.priceBetween(periodCancelled.start(),periodCancelled.end())/2);
		}else {
			System.out.println("Se paga todo "+booking.priceBetween(periodCancelled.start(),periodCancelled.end()));

		}
		
	}

}
