package sa.cancellation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;

class IntermediateCancellationTest {

	private IntermediateCancellation intermediateCancellation;
	private Booking bookingTest;

	@BeforeEach
	void setUp() throws Exception {
		//DOCS
		bookingTest = mock(Booking.class);
		
		when(bookingTest.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 24));
		when(bookingTest.price(bookingTest.getCheckIn())).thenReturn((double) 123000);
		
		//SUT
		intermediateCancellation = new IntermediateCancellation();
	}

	@Test
	void newIntermediateCancellationtest() {
		intermediateCancellation = new IntermediateCancellation();
	}
	@Test
	void setAndGetDateIntermediateCancellation() {
		intermediateCancellation.setDate(LocalDate.of(2024, 10, 31));
		assertEquals(intermediateCancellation.getDate(),LocalDate.of(2024, 10, 31));
	}
	@Test
	void activateIntermediateCancellation20DaysBeforeTest() {
		intermediateCancellation.setDate(LocalDate.of(2024, 11, 1));
		
		intermediateCancellation.activate(bookingTest);
		//System.out.println(intermediateCancellation.getDate().plusDays(40));
	}
	
	@Test
	void activateIntermediateCancellation15DaysBeforeTest() {
		intermediateCancellation.setDate(LocalDate.of(2024, 11, 9));
		
		intermediateCancellation.activate(bookingTest);
	}
	@Test
	void activateIntermediateCancellation5DaysBeforeTest() {
		intermediateCancellation.setDate(LocalDate.of(2024, 11, 19));
		intermediateCancellation.activate(bookingTest);
	}
	

}
