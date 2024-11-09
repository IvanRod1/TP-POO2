package sa.searcher.composite;
import java.util.ArrayList;

import sa.booking.Booking;
import sa.searcher.simpleQuery.IQuery;


public abstract class CompositeQuery implements IQuery{

	@Override
	public abstract ArrayList<Booking> search(ArrayList<Booking> bookings);
}
