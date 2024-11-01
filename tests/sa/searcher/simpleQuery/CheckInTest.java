package sa.searcher.simpleQuery;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.searcher.simpleQuery.CheckIn;

class CheckInTest {

	private CheckIn checkinTest;
	
	private Booking bookingMock;
	private ArrayList<Booking> aux;
	
	
	
	
	@BeforeEach
	void setUp() throws Exception {
	   checkinTest = new CheckIn(LocalDate.now());
	   bookingMock = mock(Booking.class);
	   aux = new ArrayList<Booking>();
	    
	   when(bookingMock.getCheckIn()).thenReturn(LocalDate.of(2024, 12, 20));
	   aux.add(bookingMock);
		

	}

	@Test
	void newCheckInTest() {
		checkinTest = new CheckIn(LocalDate.now());
	}
	
	@Test
	void setDateTest() {
		checkinTest.setDate(LocalDate.of(2024, 10, 30));
		assertEquals(checkinTest.getDate(),LocalDate.of(2024, 10, 30));
	}
	@Test
	void getDateTest() {
		assertEquals(checkinTest.getDate(), LocalDate.now());
	}
	
	@Test
	void checkInSearchTest() {
	
		
		checkinTest.setDate(LocalDate.of(2024, 12, 24));
		
		assertEquals(checkinTest.search(aux).size(), 1);
		
	}

}
