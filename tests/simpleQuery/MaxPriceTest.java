package simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaxPriceTest {

	private MaxPrice maxPriceFilter;
	@BeforeEach
	void setUp() throws Exception {
		maxPriceFilter = new MaxPrice(15000);
	}

	@Test
	void newMaxPriceTest() {
		
		MaxPrice filter = new MaxPrice(1000);	
	}
	
	@Test
	void maxPriceSearchTest() {
		Booking bookingMock = mock(Booking.class);
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		when(bookingMock.getValue()).thenReturn(100000);
		aux.add(bookingMock);
		
		assertEquals(maxPriceFilter.search(aux).size(),0);
	}

}
