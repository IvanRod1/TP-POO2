package CompositeQuery;
import simpleQuery.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import simpleQuery.Booking;

public class And extends CompositeQuery {
	
	private IQuery query1;
	private IQuery query2;

	@Override
	public ArrayList<Booking> search(ArrayList<Booking> bookings) {
		
		//Creo dos arrayList de Booking que cada uno va a tener los bookings filtrados segun el IQuery dado

		ArrayList<Booking> filter1 = this.query1.search(bookings);

		ArrayList<Booking> filter2 = this.query2.search(bookings); 

		
		//Creo un set de bookings, que me permita juntar los bookings fueron filtrados por ambos filtros	
		Set<Booking> aux = new LinkedHashSet<Booking>();
		
		aux.addAll(this.query1.search(filter2));
		aux.addAll(this.query2.search(filter1));
				
		ArrayList<Booking> results = new ArrayList<Booking>(aux);

		return results;
	}
	
	public And(IQuery q1,IQuery q2) {
		this.query1 = q1;
		this.query2 = q2;
	}

}
