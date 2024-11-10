package sa.booking;

import sa.users.Owner;
import sa.users.Tenant;
import sa.users.User;

public class ReserveAvailable implements IReserveState {

	private IReserveState	next;

	public ReserveAvailable() {
		// TODO Auto-generated constructor stub
		this.next = new ReserveApproved();
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
	public void approveReserve(Booking b) {
		// TODO Auto-generated method stub
		b.setState(this.next());
	}

	@Override
	public void cancelReserve(Booking b) {
		// TODO Auto-generated method stub
		b.getTenant().reserveCancelled(b);
		b.nextRequest();
	}

	@Override
	public void requestReserve(Booking b) {
		// TODO Auto-generated method stub
		b.getProperty().getOwner().reserveRequestedOn(b);
	}

}

