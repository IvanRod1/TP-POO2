package sa.cancellation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;

class NoCancellationTest {

	private NoCancellation noCancellation;
	
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		noCancellation = new NoCancellation();
		
		//DOC
		bookingTest = mock(Booking.class);
		when(bookingTest.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 24));
		when(bookingTest.price(bookingTest.getCheckIn())).thenReturn((double) 120000);
	}

	@Test
	void newNoCancellation() {
		noCancellation = new NoCancellation();
	}
	@Test
	void activateNoCancellation() {
		noCancellation.activate(bookingTest);
	}

}
