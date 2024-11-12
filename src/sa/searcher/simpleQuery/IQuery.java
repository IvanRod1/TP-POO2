package sa.searcher.simpleQuery;

import java.util.ArrayList;
import java.util.List;

import sa.booking.Booking;

public interface IQuery {
	 List<Booking> search(List<Booking> bookings);
}
