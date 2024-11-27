package sa.booking.reserveStates;

import sa.booking.Reserve;

public interface IReserveState {

	public IReserveState next();
	public void approve(Reserve r);
	public void cancel();
	public void request(Reserve r);
	public boolean isCancelled();
	public Reserve getReserve();
}
