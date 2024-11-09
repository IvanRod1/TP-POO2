package sa.booking;

import sa.users.Tenant;

public interface IReserveState {

	IReserveState next();
	public void approbeReserve(Booking b);
	public void cancelReserve();
	public void requestReserve(Tenant t);

}
