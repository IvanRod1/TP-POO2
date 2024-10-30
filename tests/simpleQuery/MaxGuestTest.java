package simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaxGuestTest {

	private MaxGuest maxGuestFilter;
	
	@BeforeEach
	void setUp() throws Exception {
		maxGuestFilter = new MaxGuest(10);
	}

	@Test
	void newMaxGuestFilterTest() {
		MaxGuest newFilter = new MaxGuest(3);
	}
	
	@Test
	void maxGuestSearchFilterTest() {
		Booking bookingMock = mock(Booking.class);
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		when(bookingMock.getMaxGuest()).thenReturn(15);
		aux.add(bookingMock);
		
		assertEquals(maxGuestFilter.search(aux).size(),1);
		
	}
	
	

}
