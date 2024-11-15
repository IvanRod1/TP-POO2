package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.BookedPeriod;
import sa.booking.Booking;
import sa.booking.IReserveState;

class ApplicationMobileTest {

	private ApplicationMobile appMobileObserver;
	private ObjectScreen screenTest;
	private Booking bookingTest;
	private IReserveState estado;
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		appMobileObserver = new ApplicationMobile();
		
		//DOC
		screenTest = mock(ObjectScreen.class);
		//when(appMobileObserver.getScreen()).thenReturn(screenTest);
		
		bookingTest = mock(Booking.class);
		estado = mock(IReserveState.class);
		when(bookingTest.getState()).thenReturn(estado);
		
	}

	@Test
	void newAppMobileObservertest() {
		appMobileObserver = new ApplicationMobile();
		assertNotNull(appMobileObserver);
	}

	@Test
	void updateObserverAppMobileTest() {
		appMobileObserver.setScreen(screenTest);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		appMobileObserver.update(bookingTest);
		assertEquals("No mando nada" + System.lineSeparator(), outContent.toString());
//		assertEquals("Se mando al objeto que publica las notificaciones el booking" + System.lineSeparator(), outContent.toString());
	}

	@Test
	void updateObserverAppMobileWithBookedPeriodTest() {
		appMobileObserver.setScreen(screenTest);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		BookedPeriod bp = mock(BookedPeriod.class);
		appMobileObserver.update(bookingTest, bp);
		assertEquals("Le paso el booking al objeto colaborativo y el bookedperiod en cuestion" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	void updateObserverAppMobileWithDateTest() {
		appMobileObserver.setScreen(screenTest);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		LocalDate date = LocalDate.now();
		appMobileObserver.update(bookingTest, date);
		assertEquals("Le paso el booking al objeto colaborativo y el date en cuestion" + System.lineSeparator(), outContent.toString());
	}
	
	 
}
