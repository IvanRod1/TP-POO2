package sa.searcher.composite;

import java.util.List;


import sa.booking.Booking;
import sa.searcher.simpleQuery.IQuery;

public class And extends CompositeQuery {
	
	private IQuery fstQuery;
	private IQuery sndQuery;
	
	public And(IQuery firstQuery,IQuery secondQuery) {
		this.fstQuery = firstQuery;
		this.sndQuery = secondQuery;
	}
	@Override
	public List<Booking> search(List<Booking> bookings) {
		
		return sndQuery.search(fstQuery.search(bookings)); 
	}

}
