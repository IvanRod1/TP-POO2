package simpleQuery;

import java.util.ArrayList;

public class MaxGuest extends SimpleQuery {

	private int value;
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		for(int i=0;i<bookings.size();i++) {
			if(bookings.get(i).getMaxGuest() >= this.value) {
				aux.add(bookings.get(i));
			}
		}
		
		return aux;
	}
	
	public MaxGuest(int max) {
		this.value = max;
	}
	
	public void setMaxGuest(int i) {
		this.value = i;
	}
	
	public int getMaxGuests() {
		return this.value;
	}

}
