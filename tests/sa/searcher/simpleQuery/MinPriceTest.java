package sa.searcher.simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.searcher.simpleQuery.MinPrice;

class MinPriceTest {

	private MinPrice minPriceFilter;
	
	private Booking bookingMock;
	private ArrayList<Booking> aux;
	
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		minPriceFilter = new MinPrice();
		
		//DOC
		bookingMock = mock(Booking.class);
		aux = new ArrayList<Booking>();
		
		when(bookingMock.getValue()).thenReturn(1040);
		aux.add(bookingMock);
		
		
	}

	@Test
	void newMinPrice() {
		MinPrice minPrice = new MinPrice();
	}
	
	@Test
	void getValueTest() {
		//MinPrice minFilterMock = mock(MinPrice.class);
		//when(minFilterMock.getValue()).thenReturn(123);
		minPriceFilter.setValue(123);
		minPriceFilter.getValue();
		assertEquals(minPriceFilter.getValue(),123);
	}
	
	@Test
	void setValueTest() {
		minPriceFilter.setValue(456);
		assertEquals(minPriceFilter.getValue(), 456);

	}
	
	@Test
	void minPriceSearchTest() {
		
		minPriceFilter.setValue(1020);
		
		assertEquals(minPriceFilter.search(aux).size(),1);
		
		
	}

}
