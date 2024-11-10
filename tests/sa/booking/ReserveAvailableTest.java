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

class ReserveAvailableTest {

	private ReserveAvailable	stateAvailable;
	private ReserveApproved		stateApproved;
	private Booking				booking;
	private Property			property;
	private Tenant				tenant;
	private Owner				owner;
	
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateApproved	= mock(ReserveApproved.class);
		this.booking		= mock(Booking.class);
		this.property		= mock(Property.class);
		this.tenant			= mock(Tenant.class);
		this.owner			= mock(Owner.class);

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.booking.getTenant()).thenReturn(this.tenant);
		when(this.property.getOwner()).thenReturn(this.owner);
		
		
		// SUT (System Under Test): objeto a testear
		this.stateAvailable = new ReserveAvailable(this.stateApproved);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateAvailable);
	}

	@Test
	void testNext() {
		assertEquals(this.stateApproved, this.stateAvailable.next());
	}
	
	@Test
	void testRequestReserve() {
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.property);
		verifyNoInteractions(this.owner);
		this.stateAvailable.requestReserve(this.booking);
		verify(this.booking, times(1)).getProperty();
		verify(this.property, times(1)).getOwner();
		verify(this.owner, times(1)).reserveRequestedOn(this.booking);
		// Simular aprobación
		verify(this.booking, times(0)).approveReserve();
		this.booking.approveReserve();
		verify(this.booking, times(1)).approveReserve();
		// Simular rechazo
		verify(this.booking, times(0)).cancelReserve();
		this.booking.cancelReserve();
		verify(this.booking, times(1)).cancelReserve();
		
	}

	@Test
	void testApproveReserve() {
		verifyNoInteractions(this.booking);
		this.stateAvailable.approveReserve(this.booking);
		verify(this.booking, times(1)).setState(this.stateApproved);
	}

	@Test
	void testCancelReserve() {
		verifyNoInteractions(this.booking);
		verifyNoInteractions(this.tenant);
		this.stateAvailable.cancelReserve(this.booking);
		verify(this.tenant, times(1)).reserveCancelled(this.booking);
		verify(this.booking, times(1)).nextRequest();
	}

}
