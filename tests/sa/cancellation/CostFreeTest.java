package sa.cancellation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sa.booking.Booking;

class CostFreeTest {

	private CostFree costfree;
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
		costfree = new CostFree();
		bookingTest = mock(Booking.class);
		
		when(bookingTest.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 15));
		when(bookingTest.price(bookingTest.getCheckIn())).thenReturn((double) 9000);
	}

	@Test
	void newCostFree() {
		costfree = new CostFree();
		assertNotNull(costfree);
	}
	
	@Test
	void activateCostFreeTest() {
		CostFree spy = Mockito.spy(costfree);
		spy.activate(LocalDate.of(2024, 11, 3),bookingTest);
		verify(spy, atLeastOnce()).activate(LocalDate.of(2024, 11, 3), bookingTest);
	}
	
	@Test
	void activateNoCostFreeTest() {
		CostFree spy = Mockito.spy(costfree);
		spy.activate(LocalDate.of(2024, 11, 9),bookingTest);
		verify(spy, atLeastOnce()).activate(LocalDate.of(2024, 11, 9), bookingTest);
	}
	

}
