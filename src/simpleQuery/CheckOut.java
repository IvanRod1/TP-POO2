package simpleQuery;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckOut extends SimpleQuery{

	private LocalDate date;
	
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		ArrayList<Booking> aux = new ArrayList<Booking>();
		for(int i=0;i<bookings.size();i++)
		{
			//bookings.get(i).getCheckOut().isBefore(this.checkout)
			if(this.date.isBefore(bookings.get(i).getCheckOut())|| bookings.get(i).getCheckOut().isEqual(this.date)) {
				aux.add(bookings.get(i));
			}
		}
		return aux;
	}
	public Object getDate() {
		return this.date;
	}
	
	public CheckOut(LocalDate date) {
		this.date = date;
	}
	public void setDate(LocalDate fecha) {
		this.date = fecha;
	}

}
