package sa.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.reserveStates.IReserveState;
import sa.booking.reserveStates.ReserveBooked;
import sa.properties.Property;
import sa.users.Owner;
import sa.users.Tenant;

class ReserveTest {

	private Reserve reserve;
	
	private Booking booking;
	private Tenant tenant;
	private Period period;
	private double price;
	private Owner owner;
	private Property property;

	private IReserveState stateBooked;
	
	private LocalDate	today;
	
	@BeforeEach
	void setUp() throws Exception {

		// DOCs
		this.booking	= mock(Booking.class);
		this.tenant		= mock(Tenant.class);
		this.period		= mock(Period.class);
		this.owner		= mock(Owner.class);
		this.property	= mock(Property.class);

		this.stateBooked	= mock(ReserveBooked.class);
		
		this.today = LocalDate.now();
		this.price	= 10;

		when(this.period.start()).thenReturn(this.today);
		when(this.period.end()).thenReturn(this.today.plusDays(1));
		when(this.booking.priceBetween(this.today, this.today.plusDays(1))).thenReturn(this.price);
		when(this.booking.getProperty()).thenReturn(this.property);
		when(this.property.getOwner()).thenReturn(this.owner);
		
		
		// SUT
		this.reserve = new Reserve(booking, tenant, period);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.reserve);
	}

	@Test
	public void testGetCheckIn() {
		assertEquals(this.today, this.reserve.getCheckIn());
	}

	@Test
	public void testGetCheckOut() {
		assertEquals(this.today.plusDays(1), this.reserve.getCheckOut());
	}

	@Test
	public void testGetTenant() {
		assertEquals(this.tenant, this.reserve.getTenant());
	}

	@Test
	public void testGetBooking() {
		assertEquals(this.booking, this.reserve.getBooking());
	}

	@Test
	public void testGetPrice() {
		assertEquals(this.price, this.reserve.getPrice());
	}

	@Test
	public void testSetState() {
		assertNull(this.reserve.getState());
		this.reserve.setState(this.stateBooked);
		assertEquals(this.stateBooked, this.reserve.getState());
	}

	@Test
	public void testFromCreatedToBooked() {
		assertNull(this.reserve.getState());
		this.reserve.setState(this.stateBooked);
		assertEquals(this.stateBooked, this.reserve.getState());
	}

	@Test
	public void testNext() {
		assertNull(this.reserve.getState());
		this.reserve.setState(this.stateBooked);
		this.reserve.next();
		verify(this.stateBooked, times(1)).next();
	}

	@Test
	public void testCancel() {
		assertNull(this.reserve.getState());
		this.reserve.setState(this.stateBooked);
		this.reserve.cancel();
		verify(this.stateBooked, times(1)).cancel();
	}

	@Test
	public void testApprove() {
		assertNull(this.reserve.getState());
		this.reserve.approve();
		assertNotNull(this.reserve.getState());
		verify(this.booking, times(1)).addReserve(this.reserve);
	}

	@Test
	public void testDecline() {
		assertNull(this.reserve.getState());
		verify(this.owner, times(0)).cleanRequestedReserve();
		verify(this.tenant, times(0)).reserveDeclined(this.reserve);
		this.reserve.decline();
		assertNull(this.reserve.getState());
		verify(this.owner, times(1)).cleanRequestedReserve();
		verify(this.tenant, times(1)).reserveDeclined(this.reserve);
	}

	@Test
	public void testSetPrice() {
		assertEquals(this.price, this.reserve.getPrice());
		this.reserve.setPrice(this.price*2);
		assertEquals(this.price*2, this.reserve.getPrice());
	}
}
