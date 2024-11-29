package sa.booking.reserveStates;

import sa.booking.Reserve;

public interface IReserveState {

	public void next();
	public void approve(Reserve r);
	public void cancel();
	public boolean isCancelled();
	public Reserve getReserve();
}
