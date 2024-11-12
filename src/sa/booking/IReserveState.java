package sa.booking;

import sa.users.Tenant;

public interface IReserveState {

	public void nextState(Booking booking);
	public void approbeReserve(Booking b);
	public void cancelReserve();
	public void requestReserve(Tenant t);

}
