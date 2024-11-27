package sa.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Reserve;

class ReserveTest {

	private Reserve reserve;

	@BeforeEach
	void setUp() throws Exception {
		
		this.reserve = new Reserve();
	}

	@Test
	public void testGetTenant() {
		assertNull(this.reserve.getTenant());
	}

	@Test
	public void testSetState() {
		assertNotNull(this.reserve.getState());
		this.reserve.setState(this.stateBooked);
		assertEquals(this.stateBooked, this.reserve.getState());
	}

	@Test
	public void testFromAvailableToBooked() {
		assertEquals(this.stateAvailable, this.reserve.getState());
		assertEquals(this.stateBooked, this.stateAvailable.next());
		this.reserve.setState(this.reserve.getState().next());
		assertEquals(this.stateBooked, this.reserve.getState());
	}

	@Test
	public void testApproveReserve() {
		this.reserve.approveReserve(bookedperiod1);
		verify(this.stateAvailable, times(1)).approveReserve(this.booking, this.bookedperiod1);
	}
	
	@Test
	public void testCancelReserve() {
		this.reserve.cancelReserve(this.bookedperiod1);
		verify(this.stateAvailable, times(1)).cancelReserve(this.booking, this.bookedperiod1);
	}
}
