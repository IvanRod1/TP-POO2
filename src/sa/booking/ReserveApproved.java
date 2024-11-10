package sa.booking;

import sa.users.Owner;
import sa.users.Tenant;

public class ReserveApproved implements IReserveState {

	private IReserveState	next;
	private IReserveState	previous;
	
	public ReserveApproved(ReserveAvailable statePrevious) {
		// TODO Auto-generated constructor stub
		this.next 	  = new ReserveCompleted();
		this.previous = statePrevious;
	}

	// Para hacer DOC del state approved
	public ReserveApproved(ReserveCompleted stateNext, ReserveAvailable statePrevious) {
		// TODO Auto-generated constructor stub
		this.next 	  = stateNext;
		this.previous = statePrevious;
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
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
