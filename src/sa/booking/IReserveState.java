package sa.booking;


public interface IReserveState {

	public IReserveState next();
	public void approveReserve(Booking b, BookedPeriod bp);
	public void cancelReserve(Booking b, BookedPeriod bp);
	public void requestReserve(Booking b, BookedPeriod bp);

}
