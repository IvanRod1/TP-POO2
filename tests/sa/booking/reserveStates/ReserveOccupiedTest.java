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

class ReserveOccupiedTest {

	private ReserveOccupied		stateOccupied;
	private Booking				booking;
	private Property			property;
	private Tenant				tenant;
	private Owner				owner;
	private Reserve				reserve;
	
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC (Depended-On-Component): nuestros doubles
		this.booking		= mock(Booking.class);
		this.property		= mock(Property.class);
		this.tenant			= mock(Tenant.class);
		this.owner			= mock(Owner.class);
		this.reserve		= mock(Reserve.class);

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.reserve.getTenant()).thenReturn(this.tenant);
		when(this.reserve.getBooking()).thenReturn(this.booking);
		when(this.property.getOwner()).thenReturn(this.owner);
		
		
		// SUT (System Under Test): objeto a testear
		this.stateOccupied = new ReserveOccupied(this.reserve);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateOccupied);
	}

	@Test
	void testNext() {
		this.stateOccupied.next();
	}

	@Test
	void testApprove() {
		this.stateOccupied.approve(this.reserve);
	}

	@Test
	void testCancel() {
		verify(this.booking, times(0)).notifySubscribersCancelled(this.reserve);
		this.stateOccupied.cancel();
		verify(this.booking, times(1)).notifySubscribersCancelled(this.reserve);
	}
	
	@Test
	void testGetReserve() {
		assertEquals(this.reserve, this.stateOccupied.getReserve());
	}

	@Test
	void testIsCancelled() {
		assertEquals(false, this.stateOccupied.isCancelled());
	}

}
