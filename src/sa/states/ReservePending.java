package sa.states;

import sa.booking.Booking;
import sa.booking.IReserveState;
import sa.users.Tenant;

public class ReservePending implements IReserveState {

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approveReserve(Booking b) {
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
