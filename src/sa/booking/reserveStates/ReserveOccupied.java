package sa.booking.reserveStates;

import java.time.LocalDate;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.Reserve;
import sa.users.Owner;
import sa.users.Tenant;

public class ReserveOccupied implements IReserveState {

	private IReserveState	next;
	private Reserve			reserve;

	public ReserveOccupied(Reserve reserve) {
		// TODO Auto-generated constructor stub
		this.reserve  = reserve;
	}

	@Override
	public IReserveState next() {
		// TODO Auto-generated method stub
		// TODO: Debería registrarse cuando finaliza?
		return this.next;
	}

	@Override
	public void request(Reserve r) {
		b.setState(this);
		// Timer con checkOut (bp.end()) para pasar al próximo estado de forma limpia
	}

	@Override
	public void approve(Reserve r) {}

	@Override
	public void cancel(Reserve r) {
		// TODO Auto-generated method stub
		// La cancelación es asentada en el sistema y se envía un mail al dueño del inmueble informándole sobre
		// el hecho. Debe ser posible enviar por email alguna de las reservas realizadas.
		b.setState(this.next());
		b.setTenant(null);
		bp.tenant().reserveCancelled(b, bp);// TODO borrar porque lo hace el sistema
		b.getProperty().getOwner().reserveCancelled(b, bp);// TODO borrar porque lo hace el sistema
		b.notifySubscribersCancelled(b, bp);  // el sistema (que ES un observer) debe registrar la cancelación y puede avisarle al propietario y al Tenant
		b.applyPolicy(bp);
		b.triggerNextRequest();
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
