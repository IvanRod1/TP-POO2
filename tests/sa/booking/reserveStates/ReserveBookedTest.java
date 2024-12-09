package sa.booking.reserveStates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.properties.Property;
import sa.users.Owner;
import sa.users.Tenant;

class ReserveBookedTest {

	private ReserveBooked		stateBooked;
	private ReserveCancelled	stateCancelled;
	private ReserveOccupied		stateOccupied;
	private Booking				booking;
	private Reserve				reserve;
	private Property			property;
	private Tenant				tenant;
	private Owner				owner;
	
	private Timer				timer;
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateCancelled	= mock(ReserveCancelled.class);
		this.stateOccupied	= mock(ReserveOccupied.class);
		this.booking		= mock(Booking.class);
		this.reserve		= mock(Reserve.class);
		this.property		= mock(Property.class);
		this.tenant			= mock(Tenant.class);
		this.owner			= mock(Owner.class);
		this.timer			= mock(Timer.class);

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.reserve.getTenant()).thenReturn(this.tenant);
		when(this.reserve.getBooking()).thenReturn(this.booking);
		when(this.property.getOwner()).thenReturn(this.owner);
		
		//when(this.stateBooked.getReserve()).thenReturn(reserve);
		when(this.booking.getTimer()).thenReturn(timer);
		
		
		
		// SUT (System Under Test): objeto a testear
		this.stateBooked = new ReserveBooked(this.stateOccupied, this.stateCancelled, this.reserve);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateBooked);
	}

//	@Test
//	void testNext() {
//		verifyNoInteractions(this.reserve);
//		this.stateBooked.next();									 este metodo ya no existe
//		verify(this.reserve, times(1)).setState(this.stateOccupied); 
//	}

//	@Test
//	void testApprove() {
//		this.stateBooked.approve(this.reserve);									este metodo ya no existe
//		verify(this.booking, times(1)).notifySubscribersReserve(this.reserve);
//	}

	@Test
	void testCancel() {
		verify(this.reserve, times(0)).setState(this.stateCancelled);
		verify(this.booking, times(0)).notifySubscribersCancelled(this.reserve);
		this.stateBooked.cancel();
		//verify(this.stateBooked.getReserve(),times(1)).setState(new ReserveCancelled(this.stateBooked.getReserve(),LocalDate.now()));
		//verify(this.booking, times(1)).handleCancellation(this.reserve); falta arreglar esto
	}

	@Test
	void testGetReserve() {
		assertEquals(this.reserve, this.stateBooked.getReserve());
	}

	@Test
	void testIsCancelled() {
		assertEquals(false, this.stateBooked.isCancelled());
	}
	
	@Test
	void testUpdate() {
		this.stateBooked.update();
		verify(reserve,times(2)).getBooking();  //Se usan dos veces, una en el metodo update, y la otra en el constructor de reserveOccupied
		verify(booking,times(2)).getTimer();
		
	}
}
