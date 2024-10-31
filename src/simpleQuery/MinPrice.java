package simpleQuery;

import java.util.ArrayList;

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
			if(bookings.get(i).getValue() >= this.getValue()) {
				aux.add(bookings.get(i));
			}
		}
		
		return aux;
	}
}
