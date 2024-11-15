package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.properties.Property;

class ReserveObserverTest {

	private ReserveObserver observer;
	private Booking bookingMock;
	@BeforeEach
	void setUp() throws Exception {
		observer = new ReserveObserver();
		
		bookingMock = mock(Booking.class);
	}

	@Test
	void newReserveObserverTest() {
		ReserveObserver observer = new ReserveObserver();
		assertNotNull(observer);
	}
	
	@Test
	void updateObserver()
	{
		Property auxProperty = mock(Property.class);
		
		when(bookingMock.getProperty()).thenReturn(auxProperty);
		observer.update(bookingMock);
		
		
		verify(auxProperty).summary();
	}

	@Test
	void updaterWithBookedPeriodTest() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		BookedPeriod bp = mock(BookedPeriod.class);
		observer.update(bookingMock, bp);
		assertEquals("Le paso el booking al objeto colaborativo y el bookedperiod en cuestion" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	void updateWithDateTest() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		LocalDate date = LocalDate.now();
		observer.update(bookingMock, date);
		assertEquals("Le paso el booking al objeto colaborativo y el date en cuestion" + System.lineSeparator(), outContent.toString());
	}
}
