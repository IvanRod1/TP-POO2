package sa.booking;

import sa.users.Owner;
import sa.users.Tenant;

public class ReserveOccupied implements IReserveState {

	private IReserveState	next;
	private IReserveState	previous;

	public ReserveOccupied(ReserveAvailable stateNext, ReserveBooked statePrevious) {
		// TODO Auto-generated constructor stub
		this.next = stateNext;
		this.previous = statePrevious;
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		// TODO: Debería registrarse cuando finaliza?
		return this.next;
	}

	@Override
	public void requestReserve(Booking b, BookedPeriod bp) {
		b.setState(this);
		// Timer con checkOut (bp.end()) para pasar al próximo estado de forma limpia
	}

	@Override
	public void approveReserve(Booking b, BookedPeriod bp) {}

	@Override
	public void cancelReserve(Booking b, BookedPeriod bp) {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		bp.tenant().reserveCancelled(b, bp);// TODO borrar porque lo hace el sistema
		b.getProperty().getOwner().reserveCancelled(b, bp);// TODO borrar porque lo hace el sistema
		b.notifySubscribersCancelled(b, bp);  // el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
		b.setState(this.next());
		b.triggerNextRequest();
	}


}
