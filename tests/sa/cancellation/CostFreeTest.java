package sa.cancellation;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;

class CostFreeTest {

	private CostFree costfree;
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
		costfree = new CostFree();
		bookingTest = mock(Booking.class);
		
		when(bookingTest.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 15));
		when(bookingTest.price()).thenReturn((double) 9000);
	}

	@Test
	void newCostFree() {
		costfree = new CostFree();
	}
	
	@Test
	void activateCostFreeTest() {
		
		costfree.setDate(LocalDate.of(2024, 11, 2));
		costfree.activate(bookingTest);
	}
	
	@Test
	void activateNoCostFreeTest() {
		costfree.setDate(LocalDate.of(2024, 11, 9));
		costfree.activate(bookingTest);
	}
	

}
