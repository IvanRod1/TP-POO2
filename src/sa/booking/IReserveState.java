package sa.booking;

import sa.users.Tenant;

public interface IReserveState {

	IReserveState next();
	public void approveReserve(Booking b);
	public void cancelReserve();
	public void requestReserve(Tenant t);

}
