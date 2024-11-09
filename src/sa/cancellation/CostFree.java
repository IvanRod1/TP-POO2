package sa.cancellation;

import java.time.LocalDate;

import sa.booking.Booking;

public class CostFree implements ICancellationPolicy{

	private LocalDate date;
	@Override
	public void activate(Booking booking) {
		// TODO Auto-generated method stub
		if(this.date.isBefore(booking.getCheckIn().minusDays(10))) {
			System.out.println("No pagas nada");
		}
		else {
			System.out.println("Solo pagas el equivalente a dos dias de alquiler $:"+ booking.price(booking.getCheckIn()) * 2 );
		}
	}
	
	public void setDate(LocalDate d) {
		this.date = d;
	}
	


}
