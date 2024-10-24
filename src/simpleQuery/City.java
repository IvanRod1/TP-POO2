package simpleQuery;

import java.util.ArrayList;

public class City extends SimpleQuery {
	private String name;
	
	public City(String city) {
		this.name = city;
		this.bookings = new ArrayList<Booking>();
	}

	public String getCity() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setCity(String cityName) {
		// TODO Auto-generated method stub
		this.name = cityName;
	}

	public ArrayList<Booking> getBookings() {
		// TODO Auto-generated method stub
		return this.bookings;
	}

	public void addBooking(Booking booking) {
		// TODO Auto-generated method stub
		this.bookings.add(booking);
		
	}
	
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings){
		
		ArrayList<Booking> aux = new ArrayList<Booking>();
		for(int i=0;i < bookings.size();i++) {
			if(bookings.get(i).getCity() == this.name) {
				//this.addBooking(this.bookings.get(i));
				aux.add(bookings.get(i));
			}
		}
		return aux;
	}
	
}
