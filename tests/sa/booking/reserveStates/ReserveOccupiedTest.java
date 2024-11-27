package sa.booking.reserveStates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.reserveStates.ReserveAvailable;
import sa.booking.reserveStates.ReserveBooked;
import sa.booking.reserveStates.ReserveOccupied;
import sa.properties.Property;
import sa.users.Owner;
import sa.users.Tenant;

class ReserveOccupiedTest {

	private ReserveBooked		stateBooked;
	private ReserveAvailable	stateAvailable;
	private ReserveOccupied	stateOccupied;
	private Booking				booking;
	private Property			property;
	private Tenant				tenant;
	private Owner				owner;
	private BookedPeriod		bp;
	
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateAvailable	= mock(ReserveAvailable.class);
		this.stateBooked	= mock(ReserveBooked.class);
		this.booking		= mock(Booking.class);
		this.property		= mock(Property.class);
		this.tenant			= mock(Tenant.class);
		this.owner			= mock(Owner.class);
		this.bp				= mock(BookedPeriod.class);

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.booking.getTenant()).thenReturn(this.tenant);
		when(this.property.getOwner()).thenReturn(this.owner);
		when(this.bp.tenant()).thenReturn(this.tenant);
		
		
		// SUT (System Under Test): objeto a testear
		this.stateOccupied = new ReserveOccupied(this.stateAvailable, this.stateBooked);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateOccupied);
	}

	@Test
	void testNext() {
		assertEquals(this.stateAvailable, this.stateOccupied.next());
	}
	
	@Test
	void testRequestReserve() {
		verify(this.booking, times(0)).setState(stateOccupied);
		this.stateOccupied.requestReserve(this.booking, this.bp);
		verify(this.booking, times(1)).setState(stateOccupied);
	}

	@Test
	void testCancelReserve() {
		this.stateOccupied.cancelReserve(this.booking, bp);
		verify(this.booking, times(1)).setState(stateAvailable);
		verify(this.booking, times(1)).setTenant(null);
		verify(this.tenant, times(1)).reserveCancelled(this.booking, this.bp);
		verify(this.owner, times(1)).reserveCancelled(this.booking, this.bp);
		verify(this.booking, times(1)).notifySubscribersCancelled(this.booking, this.bp);
		verify(this.booking, times(1)).triggerNextRequest();
	}
	
	@Test
	void testApproveReserve() {}

}
