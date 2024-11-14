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

import sa.booking.BookedPeriod;
import sa.booking.Booking;

class NoCancellationTest {

	private NoCancellation noCancellation;
	
	private Booking bookingTest;
	private BookedPeriod bookedPeriodMock;
	
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		noCancellation = new NoCancellation();
		
		//DOC
		bookingTest = mock(Booking.class);
		bookedPeriodMock = mock(BookedPeriod.class);
		
		when(bookedPeriodMock.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 24));
		when(bookingTest.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 24));
		when(bookingTest.price(bookingTest.getCheckIn())).thenReturn((double) 120000);
	}

	@Test
	void newNoCancellation() {
		noCancellation = new NoCancellation();
		assertNotNull(noCancellation);
	}
	@Test
	void activateNoCancellation() {
		NoCancellation spy = Mockito.spy(noCancellation);
		spy.activate(LocalDate.now(),bookingTest,bookedPeriodMock);
		verify(spy, atLeastOnce()).activate(LocalDate.now(), bookingTest,bookedPeriodMock);

	}

}
