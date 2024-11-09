package sa.booking;

import sa.users.Tenant;

public class ReserveApproved implements IReserveState {

	private IReserveState	next = (IReserveState) new ReserveCompleted();

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		return this.next;
	}

	@Override
	public void approbeReserve(Booking b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelReserve() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestReserve(Tenant t) {
		// TODO Auto-generated method stub
		
	}

}
