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
	
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateAvailable	= mock(ReserveAvailable.class);
		this.stateOccupied	= mock(ReserveOccupied.class);
		this.booking		= mock(Booking.class);
		this.property		= mock(Property.class);
		this.tenant			= mock(Tenant.class);
		this.owner			= mock(Owner.class);

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.booking.getTenant()).thenReturn(this.tenant);
		when(this.property.getOwner()).thenReturn(this.owner);
		
		
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
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.property);
		verifyNoInteractions(this.owner);
		this.stateBooked.requestReserve(this.booking);
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.property);
		verifyNoInteractions(this.owner);
	}

	@Test
	void testApproveReserve() {
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.property);
		verifyNoInteractions(this.owner);
		this.stateBooked.approveReserve(this.booking);
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.property);
		verifyNoInteractions(this.owner);
	}

	@Test
	void testCancelReserve() {
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.tenant);
		verifyNoInteractions(this.owner);
		this.stateBooked.cancelReserve(this.booking);
		verify(this.booking, times(1)).getTenant();
		verify(this.booking, times(1)).getProperty();
		verify(this.booking, times(1)).triggerNextRequest();
		verify(this.tenant, times(1)).reserveCancelled(this.booking);
		verify(this.owner, times(1)).reserveCancelled(this.booking);
	}

}
