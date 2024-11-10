package sa.booking;

import sa.users.Owner;
import sa.users.Tenant;

public class ReserveCompleted implements IReserveState {

	private IReserveState	next;
	private IReserveState	previous;

	public ReserveCompleted(ReserveAvailable stateNext, ReserveApproved statePrevious) {
		// TODO Auto-generated constructor stub
		this.next = stateNext;
		this.previous = statePrevious;
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		// TODO: Debería registrarse cuando finaliza?
		return this.next;
	}

	@Override
	public void requestReserve(Booking b) {}

	@Override
	public void approveReserve(Booking b) {}

	@Override
	public void cancelReserve(Booking b) {
		// TODO Auto-generated method stub
		b.getTenant().reserveCancelled(b);
		b.getProperty().getOwner().reserveCancelled(b);
		b.triggerNextRequest();
	}


}
