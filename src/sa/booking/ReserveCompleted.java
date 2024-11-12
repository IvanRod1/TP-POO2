package sa.booking;

import sa.users.Tenant;

public class ReserveCompleted implements IReserveState {


	@Override
	public void nextState(Booking booking) {
		 
		booking.setState(new ReserveApproved());
	
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
