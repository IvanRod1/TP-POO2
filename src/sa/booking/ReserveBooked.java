package sa.booking;

import sa.users.Owner;
import sa.users.Tenant;

public class ReserveBooked implements IReserveState {

	private IReserveState	next;
	private IReserveState	previous;
	
	public ReserveBooked(ReserveAvailable statePrevious) {
		// TODO Auto-generated constructor stub
		this.next 	  = new ReserveOccupied(statePrevious, this);
		this.previous = statePrevious;
	}

	// Para hacer DOC del state approved
	public ReserveBooked(ReserveOccupied stateNext, ReserveAvailable statePrevious) {
		// TODO Auto-generated constructor stub
		this.next 	  = stateNext;
		this.previous = statePrevious;
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		return this.next;
	}

	@Override
	public void requestReserve(Booking b, BookedPeriod bp) {
		b.setState(this);
		b.getProperty().getOwner().reserveRequestedOn(b, bp); // Se le avisa al propietario que decida 
	}

	@Override
	public void approveReserve(Booking b, BookedPeriod bp) {
		// Hay que notificar que se acaba de reservar en un periodo cualquiera
		b.nofifySubscribersReserve(b, bp); // Avisa que se efectivizó la ReserveBooked
		this.next.requestReserve(b, bp);
	}

	@Override
	public void cancelReserve(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		bp.tenant().reserveCancelled(b, bp); // TODO borrar porque lo hace el sistema
		b.getProperty().getOwner().reserveCancelled(b, bp); // TODO borrar porque lo hace el sistema
		b.nofifySubscribersCancelled(b, bp);  // el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
		b.setState(this.previous);
		b.triggerNextRequest();
	}


}
