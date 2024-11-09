package sa.cancellation;

import java.time.LocalDate;

import sa.booking.Booking;

public class IntermediateCancellation implements ICancellationPolicy{

	private LocalDate date;
	@Override
	public void activate(Booking booking) {
		// TODO Auto-generated method stub
		if(this.date.plusDays(20).isBefore(booking.getCheckIn())) {
			System.out.println("No pagas nada");
			//this.date.plusDays(19).isAfter(booking.getCheckIn()) 
		}else if(this.date.plusDays(10).isBefore(booking.getCheckIn())) {
			System.out.println("Se paga la mitad "+booking.price()/2);
		}else {
			System.out.println("Se paga todo "+booking.price());
		}
	}
	public void setDate(LocalDate date) {
		// TODO Auto-generated method stub
		this.date = date;
		
	}
	
	public LocalDate getDate() {
		return this.date;
	}

}
