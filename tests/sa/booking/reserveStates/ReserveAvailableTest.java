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
import sa.properties.Property;
import sa.users.Owner;
import sa.users.Tenant;

class ReserveAvailableTest {

	private ReserveAvailable	stateAvailable;
	private ReserveBooked		stateBooked;
	private Booking				booking;
	private Property			property;
	private Tenant				tenant;
	private Owner				owner;
	private BookedPeriod		bp;
	private LocalDate			date;
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateBooked	= mock(ReserveBooked.class);
		this.booking		= mock(Booking.class);
		this.property		= mock(Property.class);
		this.tenant			= mock(Tenant.class);
		this.owner			= mock(Owner.class);
		this.bp				= mock(BookedPeriod.class);
		this.date			= LocalDate.now();

		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.booking.getTenant()).thenReturn(this.tenant);
		when(this.property.getOwner()).thenReturn(this.owner);
		when(this.bp.belongs(this.date)).thenReturn(true);
		
		
		// SUT (System Under Test): objeto a testear
		this.stateAvailable = new ReserveAvailable(this.stateBooked);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.stateAvailable);
	}

	@Test
	void testNext() {
		assertEquals(this.stateBooked, this.stateAvailable.next());
	}
	
	@Test
	void testRequestReserve() {
		verify(this.stateBooked, times(0)).requestReserve(this.booking, this.bp);
		this.stateAvailable.requestReserve(this.booking, this.bp);
		verify(this.stateBooked, times(1)).requestReserve(this.booking, this.bp);	
	}

	@Test
	void testApproveReserve() {}

	@Test
	void testCancelReserve() {}

}
