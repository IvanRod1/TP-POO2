package sa.searcher.simpleQuery;

import java.time.LocalDate;
import java.util.ArrayList;

import sa.booking.Booking;

public class CheckOut extends SimpleQuery{

	private LocalDate date;
	
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		ArrayList<Booking> aux = new ArrayList<Booking>();
		for(int i=0;i<bookings.size();i++)
		{
			//bookings.get(i).getCheckOut().isBefore(this.checkout)
			if(this.getDate().isBefore(bookings.get(i).getCheckOut())|| bookings.get(i).getCheckOut().isEqual(this.getDate())) {
				aux.add(bookings.get(i));
			}
		}
		return aux;
	}
	public LocalDate getDate() {
		return this.date;
	}
	
	public CheckOut(LocalDate date) {
		this.date = date;
	}
	public void setDate(LocalDate fecha) {
		this.date = fecha;
	}

}
