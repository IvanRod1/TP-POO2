package sa.searcher.simpleQuery;

import java.util.ArrayList;

import sa.booking.Booking;

public abstract class SimpleQuery implements IQuery {
	
	@Override
	public abstract ArrayList<Booking> search(ArrayList<Booking> bookings);
	
	
	
}
