package CompositeQuery;
import java.util.ArrayList;

import simpleQuery.*;

public abstract class CompositeQuery implements IQuery{

	@Override
	public abstract ArrayList<Booking> search(ArrayList<Booking> bookings);
}
