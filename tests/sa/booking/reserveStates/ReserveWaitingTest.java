package sa.booking.reserveStates;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Reserve;

class ReserveWaitingTest {

	private ReserveWaiting stateReserve;
	private Reserve reserve;
	private Booking booking;
	private Timer timer;
	@BeforeEach
	void setUp() throws Exception {
		reserve = mock(Reserve.class);
		timer = mock(Timer.class);
		stateReserve = new ReserveWaiting(this.reserve);
		booking = mock(Booking.class);
		when(reserve.getBooking()).thenReturn(booking);
		when(booking.getTimer()).thenReturn(timer);
	}

	@Test
	void newReserveStateTest() {
		assertNotNull(stateReserve);
	}
	@Test
	void isCancellatedTest() {
		assertFalse(stateReserve.isCancelled());
	}
	@Test
	void cancelTest() {
		stateReserve.cancel();
		verify(this.booking,times(1)).removeWaiting(this.stateReserve.getReserve());
	}
	@Test
	void updateTest() {
		stateReserve.update();
		//assertEquals(reserve.getState(),any(ReserveBooked.class));
		//verify(this.reserve,times(1)).setState(new ReserveBooked(this.stateReserve.getReserve())); falta verificar esta linea si es posible
	}

}
