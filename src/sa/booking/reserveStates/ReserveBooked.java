package sa.booking.reserveStates;


import java.time.LocalDate;

import sa.booking.Reserve;

public class ReserveBooked implements IReserveState {

	private IReserveState	nextState = null;
	private IReserveState	cancelState;
	private Reserve			reserve;
	

	public ReserveBooked(Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.reserve  	  = reserve;
//		this.nextState 	  = new ReserveOccupied(this.reserve);
		this.getReserve().getBooking().getTimer().register(this, this.getReserve().getCheckIn());
	}

	// Para hacer DOC del state approved
	public ReserveBooked(ReserveOccupied stateNext, ReserveCancelled stateCancel, Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.nextState 	 = stateNext;
		this.cancelState = stateCancel;
		this.reserve 	 = reserve;
	}


	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		this.getReserve().setState(new ReserveCancelled(this.getReserve(), LocalDate.now()));
		// el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
		this.getReserve().cancelled();
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

	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.getReserve().getBooking().getTimer().unregister(this, this.getReserve().getCheckIn());
		this.getReserve().setState(new ReserveOccupied(this.getReserve()));
	}


}
