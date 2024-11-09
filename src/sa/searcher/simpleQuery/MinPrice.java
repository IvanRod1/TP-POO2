package sa.searcher.simpleQuery;

import java.util.ArrayList;

import sa.booking.Booking;

public class MinPrice {
	private int value;

	public int getValue() {
		return this.value;
	}

	public void setValue(int i) {
		this.value = i;
	}

	public ArrayList<Booking> search(ArrayList<Booking> bookings) {

		ArrayList<Booking> aux = new ArrayList<Booking>();
		for(int i=0;i<bookings.size();i++) {
			if(bookings.get(i).price(bookings.get(i).getCheckIn()) >= this.getValue()) {  //chequear
				aux.add(bookings.get(i));
			}
		}
		
		return aux;
	}
}
