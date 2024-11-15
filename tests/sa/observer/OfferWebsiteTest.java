package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.BookedPeriod;
import sa.booking.Booking;

class OfferWebsiteTest {

	private OfferWebSite website;
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
	
		
		//SUT
		website = new OfferWebSite();
		
		//DOC
		//bookingTest = mock(Booking.class);
		
	}

	@Test
	void newOfferWebSitetest() {
		assertNotNull(website);
	}

	@Test
	void updateTest() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		website.update(bookingTest);
		assertEquals("Le paso el booking al objeto colaborativo" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	void updateOWithBookedPeriodTest() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		BookedPeriod bp = mock(BookedPeriod.class);
		website.update(bookingTest, bp);
		assertEquals("Le paso el booking al objeto colaborativo y el bookedperiod en cuestion" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	void updateWithDateTest() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		LocalDate date = LocalDate.now();
		website.update(bookingTest, date);
		assertEquals("Le paso el booking al objeto colaborativo y el date en cuestion" + System.lineSeparator(), outContent.toString());
	}

}
