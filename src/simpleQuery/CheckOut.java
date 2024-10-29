package simpleQuery;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckOut extends SimpleQuery{

	private LocalDate checkout;
	
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		ArrayList<Booking> aux = new ArrayList<Booking>();
		for(int i=0;i<bookings.size();i++)
		{
			//bookings.get(i).getCheckOut().isBefore(this.checkout)
			if(this.checkout.isBefore(bookings.get(i).getCheckOut())|| bookings.get(i).getCheckOut().isEqual(this.checkout)) {
				aux.add(bookings.get(i));
			}
		}
		return aux;
	}
	public Object getDate() {
		return this.checkout;
	}
	
	public CheckOut() {
		this.checkout = null;
	}
	public void setDate(LocalDate fecha) {
		this.checkout = fecha;
	}

}
