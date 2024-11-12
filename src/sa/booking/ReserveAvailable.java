package sa.booking;

import sa.users.Owner;
import sa.users.Tenant;
import sa.users.User;

public class ReserveAvailable implements IReserveState {

	private IReserveState	next;

	public ReserveAvailable() {
		// TODO Auto-generated constructor stub
		this.next = new ReserveBooked(this);
	}
	
	// Para hacer DOC del state available
	public ReserveAvailable(IReserveState state) {
		// TODO Auto-generated constructor stub
		this.next = state;
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		return this.next;
	}
	
	@Override
	public void requestReserve(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.next().requestReserve(b, bp);
	}

	@Override
	public void approveReserve(Booking b, BookedPeriod bp) {}
		// TODO Auto-generated method stub

	@Override
	public void cancelReserve(Booking b, BookedPeriod bp) {}
	// TODO Auto-generated method stub

}

