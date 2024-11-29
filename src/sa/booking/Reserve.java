package sa.booking;

import java.time.LocalDate;

import sa.booking.reserveStates.IReserveState;
import sa.booking.reserveStates.ReserveBooked;
import sa.users.Tenant;

public class Reserve {

	private Booking booking;
	private Period period;
	private Tenant tenant;
	private double price;
	private IReserveState state;

	public Reserve(Booking b, Tenant t, Period p) {
		// TODO Auto-generated constructor stub
		this.booking = b;
		this.period	 = p;
		this.tenant	 = t;
		this.price	 = this.booking.priceBetween(this.getCheckIn(), this.getCheckOut());
	}

	public void setState(IReserveState state) {
		// TODO Auto-generated method stub
		this.state = state;
	}

	public IReserveState getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	public Tenant getTenant() {
		// TODO Auto-generated method stub
		return this.tenant;
	}

	public void approve() { // El Owner aprueba al Tenant solicitado.
		// TODO Auto-generated method stub
		this.setState(new ReserveBooked(this));
		this.getBooking().addReserve(this); // Acá se encarga el booking de gestionar el cobro, la subscripción al Timer y demás
	}
	
	public void cancel() {
		// TODO Auto-generated method stub
		this.state.cancel();
	}

	public Period getPeriod() {
		// TODO Auto-generated method stub
		return this.period;
	}

	public LocalDate getCheckIn() {
		// TODO Auto-generated method stub
		return this.getPeriod().start();
	}

	public LocalDate getCheckOut() {
		// TODO Auto-generated method stub
		return this.getPeriod().end();
	}

	public Booking getBooking() {
		// TODO Auto-generated method stub
		return this.booking;
	}

	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	
	void next() {
		// TODO Auto-generated method stub
		this.state.next();
	}

}
