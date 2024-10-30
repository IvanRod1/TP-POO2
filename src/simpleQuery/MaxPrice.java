package simpleQuery;

import java.util.ArrayList;

public class MaxPrice extends SimpleQuery {

	private int value;
	
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		
		ArrayList<Booking> aux = new ArrayList<Booking>();
		for(int i=0;i<bookings.size();i++) {
			if(bookings.get(i).getValue() <= this.value){
				aux.add(bookings.get(i));
			}
		}
		return aux;
	}
	
	public MaxPrice(int max) {
		this.value = max;
	}
	

}
