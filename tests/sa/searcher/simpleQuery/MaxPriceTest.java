package sa.searcher.simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.searcher.simpleQuery.MaxPrice;

class MaxPriceTest {

	private MaxPrice maxPriceFilter;
	
	private Booking bookingMock;
	private ArrayList<Booking> aux;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		maxPriceFilter = new MaxPrice(15000);
		
		
		//DOC
		bookingMock = mock(Booking.class);
	    aux = new ArrayList<Booking>();
	    
	    LocalDate fechaAuxiliar = LocalDate.of(2024, 11, 20);
	    when(bookingMock.getCheckIn()).thenReturn(fechaAuxiliar);
	    when(bookingMock.price(bookingMock.getCheckIn())).thenReturn((double) 100000);
		aux.add(bookingMock);
	}

	@Test
	void newMaxPriceTest() {
		
		MaxPrice filter = new MaxPrice(1000);	
	}
	
	@Test
	void maxPriceSearchTest() {
		
		assertEquals(maxPriceFilter.search(aux).size(),0);
	}
	@Test
	void getValueTest() {
		assertEquals(maxPriceFilter.getValue(),15000);

	}

}
