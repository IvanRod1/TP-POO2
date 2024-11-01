package sa.searcher.composite;

import simpleQuery.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import sa.booking.Booking;
import sa.searcher.simpleQuery.IQuery;

public class Or extends CompositeQuery {
	
	
	private IQuery query1;
	private IQuery query2;
	
	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		
		Set<Booking> aux = new LinkedHashSet<Booking>();
		
		ArrayList<Booking> query1 = this.query1.search(bookings);
		ArrayList<Booking> query2 = this.query2.search(bookings);
		
		aux.addAll(query1);
		aux.addAll(query2);
		
		ArrayList<Booking> results = new ArrayList<Booking>(aux);

		
		return results;
	}
	
	public Or(IQuery q1,IQuery q2) {
		this.query1=q1;
		this.query2=q2;
	}

}
