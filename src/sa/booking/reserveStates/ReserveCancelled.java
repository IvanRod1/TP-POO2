package sa.booking.reserveStates;

import sa.booking.Reserve;

public class ReserveCancelled implements IReserveState {

	public ReserveCancelled(Reserve reserve) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approve(Reserve r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void request(Reserve r) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reserve getReserve() {
		// TODO Auto-generated method stub
		return null;
	}

}
