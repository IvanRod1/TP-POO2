package sa.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.properties.Property;
import sa.users.Owner;
import sa.users.Tenant;

class ReserveBookedTest {

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
		this.stateOccupied	= mock(ReserveOccupied.class);
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
		this.stateBooked = new ReserveBooked(this.stateOccupied, this.stateAvailable);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateBooked);
	}

	@Test
	void testNext() {
		assertEquals(this.stateOccupied, this.stateBooked.next());
	}
	
	@Test
	void testRequestReserve() {
		verify(this.booking, times(0)).setState(stateBooked);
		this.stateBooked.requestReserve(this.booking, this.bp);
		verify(this.booking, times(1)).setState(stateBooked);
		
//		
//		verify(this.stateBooked, times(1)).requestReserve(this.booking, this.bp);
//		verify(this.booking, times(1)).getProperty();
//		verify(this.property, times(1)).getOwner();
//		verify(this.owner, times(1)).reserveRequestedOn(this.booking, this.bp);
//		// Simular aprobación
//		verify(this.booking, times(0)).approveReserve(this.bp);
//		this.booking.approveReserve(this.bp);
//		verify(this.booking, times(1)).approveReserve(this.bp);
//		// Simular rechazo
//		verify(this.booking, times(0)).cancelReserve(this.bp);
//		this.booking.cancelReserve(this.bp);
//		verify(this.booking, times(1)).cancelReserve(this.bp);
//
//		this.stateBooked.requestReserve(this.booking);
//		verifyNoInteractions(this.booking);
//		verifyNoInteractions(this.property);
//		verifyNoInteractions(this.owner);
	}

	@Test
	void testApproveReserve() {
		this.stateBooked.approveReserve(this.booking, this.bp);
		verify(this.booking, times(1)).setTenant(this.tenant);
		verify(this.booking, times(1)).notifySubscribersReserve(this.booking, this.bp);
		verify(this.stateOccupied, times(1)).requestReserve(this.booking, this.bp);
	}

	@Test
	void testCancelReserve() {
		this.stateBooked.cancelReserve(this.booking, this.bp);
		verify(this.booking, times(1)).setState(stateAvailable);
		verify(this.booking, times(1)).setTenant(null);
		verify(this.tenant, times(1)).reserveCancelled(this.booking, this.bp);
		verify(this.owner, times(1)).reserveCancelled(this.booking, this.bp);
		verify(this.booking, times(1)).notifySubscribersCancelled(this.booking, this.bp);
		verify(this.booking, times(1)).triggerNextRequest();
	
//		this.stateBooked.cancelReserve(this.booking, this.bp);
//		verify(this.booking, times(1)).getTenant();
//		verify(this.booking, times(1)).getProperty();
//		verify(this.booking, times(1)).triggerNextRequest();
//		verify(this.tenant, times(1)).reserveCancelled(this.booking, this.bp);
//		verify(this.owner, times(1)).reserveCancelled(this.booking, this.bp);
	}

}
