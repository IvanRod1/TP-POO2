package sa.simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Period;

class CheckInTest {

	private CheckIn querytest1;
	private CheckIn querytest2;
	private Booking bookingMock;
	
	private List<Booking> bookings;
	
	private Period bookingPeriod;
	
	@BeforeEach
	void setUp() throws Exception {
		
		querytest1 = new CheckIn(LocalDate.of(2024, 11, 20));
		querytest2 = new CheckIn(LocalDate.of(2024, 8, 17));
		
		bookingMock = mock(Booking.class);
		
		bookingPeriod = mock(Period.class);
		
		when(bookingPeriod.start()).thenReturn(LocalDate.of(2024, 10, 10));
		when(bookingPeriod.end()).thenReturn(LocalDate.of(2024, 12, 31));
		when(bookingMock.getPeriod()).thenReturn(bookingPeriod);
		
		bookings.add(bookingMock);
	}

	@Test
	void newCheckInTest() {
		assertNotNull(querytest1);
		assertNotNull(querytest2);
	}
	
	@Test
	void successfulQuerySearchTest() {
		
		assertEquals(this.querytest1.search(bookings).size(),1);
	}

}
