package sa.booking.reserveStates;

import java.time.LocalDate;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.subscriptions.INotifyTimerSubscriber;
import sa.users.Owner;
import sa.users.Tenant;

public class ReserveBooked implements IReserveState {

	private IReserveState	next;
	private Reserve			reserve;
	

	public ReserveBooked(Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.next 	  = new ReserveOccupied(reserve);
		this.reserve  = reserve;
	}

	// Para hacer DOC del state approved
	public ReserveBooked(ReserveOccupied stateNext, ReserveAvailable statePrevious) {
		// TODO Auto-generated constructor stub
		this.next 	  = stateNext;
		this.previous = statePrevious;
	}


	@Override
	public void next() {
		// TODO Auto-generated method stub
		this.getReserve().setState(this.next());
	}

	@Override
	public void request(Reserve r) {
		b.setState(this);
		b.getProperty().getOwner().reserveRequestedOn(b, bp); // Se le avisa al propietario que decida para el bookedperiod actual
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		this.getReserve().setState(new ReserveCancelled(this.getReserve()));
		this.getReserve().getBooking().notifySubscribersCancelled(this.getReserve());
		bp.tenant().reserveCancelled(b, bp); // TODO borrar porque lo hace el sistema
		b.getProperty().getOwner().reserveCancelled(b, bp); // TODO borrar porque lo hace el sistema
		b.notifySubscribersCancelled(b, bp);  // el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
		b.applyPolicy(bp);
		b.triggerNextRequest();
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
		return null;
	}
}
