package sa.booking;


public interface IReserveState {

	public IReserveState next();
	public void approveReserve(Booking b);
	public void cancelReserve(Booking b);
	public void requestReserve(Booking b);

}
