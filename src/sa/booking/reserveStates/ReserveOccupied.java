package sa.booking.reserveStates;

import java.time.LocalDate;

import sa.booking.Reserve;

public class ReserveOccupied implements IReserveState {

	private Reserve			reserve;

	public ReserveOccupied(Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.reserve  = reserve;
	}

	@Override
	public void next() {}

	@Override
	public void approve(Reserve r) {}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		this.getReserve().setState(new ReserveCancelled(this.getReserve(), LocalDate.now()));
		this.getReserve().getBooking().notifySubscribersCancelled(this.getReserve());  // el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reserve getReserve() {
		// TODO Auto-generated method stub
		return this.reserve;
	}
}
