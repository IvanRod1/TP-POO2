package sa.searcher.simpleQuery;

import java.util.ArrayList;
import java.util.List;

import sa.booking.Booking;

public class City extends SimpleQuery {

	private String name;
	
	public City(String cityName) {
		this.name = cityName;
	}
	
	@Override
	public List<Booking> search(List<Booking> bookings) {
		List<Booking> aux = new ArrayList<Booking>();
		
		for(int i=0;i<bookings.size();i++) {
			if(bookings.get(i).getProperty().getCity().equals(name)) {
				aux.add(bookings.get(i));
			}
		}
		
		return aux;
	}

}
