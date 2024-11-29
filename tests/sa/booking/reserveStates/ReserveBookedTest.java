package sa.booking.reserveStates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

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

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.reserve.getTenant()).thenReturn(this.tenant);
		when(this.reserve.getBooking()).thenReturn(this.booking);
		when(this.property.getOwner()).thenReturn(this.owner);
		
		
		// SUT (System Under Test): objeto a testear
		this.stateBooked = new ReserveBooked(this.stateOccupied, this.stateCancelled, this.reserve);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateBooked);
	}

	@Test
	void testNext() {
		verifyNoInteractions(this.reserve);
		this.stateBooked.next();
		verify(this.reserve, times(1)).setState(this.stateOccupied); 
	}

	@Test
	void testApprove() {
		this.stateBooked.approve(this.reserve);
		verify(this.booking, times(1)).notifySubscribersReserve(this.reserve);
	}

	@Test
	void testCancel() {
		verify(this.reserve, times(0)).setState(this.stateCancelled);
		verify(this.booking, times(0)).notifySubscribersCancelled(this.reserve);
		this.stateBooked.cancel();
		verify(this.booking, times(1)).notifySubscribersCancelled(this.reserve);
	}

	@Test
	void testGetReserve() {
		assertEquals(this.reserve, this.stateBooked.getReserve());
	}

	@Test
	void testIsCancelled() {
		assertEquals(false, this.stateBooked.isCancelled());
	}
}
