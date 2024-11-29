package sa.booking.reserveStates;


import java.time.LocalDate;

import sa.booking.Reserve;

public class ReserveBooked implements IReserveState {

	private IReserveState	nextState;
	private IReserveState	cancelState;
	private Reserve			reserve;
	

	public ReserveBooked(Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.reserve  	  = reserve;
		this.nextState 	  = new ReserveOccupied(this.reserve);
	}

	// Para hacer DOC del state approved
	public ReserveBooked(ReserveOccupied stateNext, ReserveCancelled stateCancel, Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.nextState 	 = stateNext;
		this.cancelState = stateCancel;
		this.reserve 	 = reserve;
	}


	@Override
	public void next() {
		// TODO Auto-generated method stub
		this.getReserve().setState(this.nextState);
	}
	
	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		this.getReserve().setState(new ReserveCancelled(this.getReserve(), LocalDate.now()));
		// el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
		this.getReserve().getBooking().notifySubscribersCancelled(this.getReserve());
	}

	@Override
	public void approve(Reserve r) {
		// TODO Auto-generated method stub
		// Hay que notificar que se acaba de reservar en un periodo cualquiera
		r.getBooking().notifySubscribersReserve(r); // Avisa que se efectivizó la ReserveBooked
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
