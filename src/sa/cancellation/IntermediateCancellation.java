package sa.cancellation;

import java.time.LocalDate;

import sa.booking.Booking;

public class IntermediateCancellation implements ICancellationPolicy{

	@Override
	public void activate(LocalDate date,Booking booking) {
		/*
		 * Este metodo se encarga de verificar la fecha de cancelacion del booking con la fecha de activacion de la politica
		 * Si la fecha de cancelacion es 20 dias previa, no se paga nada y se lo notifica en pantalla
		 * Si es 10 dias previa, se paga la mitad del precio
		 * Y si no es ninguna de las 2, se paga todo 
		 * */
		if(date.plusDays(20).isBefore(booking.getCheckIn())) {
			System.out.println("No pagas nada");
			//this.date.plusDays(19).isAfter(booking.getCheckIn()) 
		}else if(date.plusDays(10).isBefore(booking.getCheckIn())) {
			System.out.println("Se paga la mitad "+booking.priceBetween(booking.getCheckIn(),booking.getCheckOut())/2);
		}else {
			System.out.println("Se paga todo "+booking.priceBetween(booking.getCheckIn(),booking.getCheckOut()));
		}
	}

}
