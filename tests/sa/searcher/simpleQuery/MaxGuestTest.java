package sa.searcher.simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.properties.Property;
import sa.searcher.simpleQuery.MaxGuest;

class MaxGuestTest {

	private MaxGuest maxGuestFilter;
	
	private Booking bookingMock;
	private Property property;
	
	private ArrayList<Booking> aux;
	
	
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		maxGuestFilter = new MaxGuest(10);
		
		//DOC
		bookingMock = mock(Booking.class);
		property = mock(Property.class);
		aux = new ArrayList<Booking>();
		
		when(this.bookingMock.getProperty()).thenReturn(property);
		when(property.getMaxGuests()).thenReturn(15);
		aux.add(bookingMock);
	}

	@Test
	void newMaxGuestFilterTest() {
		MaxGuest newFilter = new MaxGuest(3);
	}
	
	@Test
	void maxGuestSearchFilterTest() {
		
		assertEquals(maxGuestFilter.search(aux).size(),1);
		
		
	}
	@Test
	void getMaxGuestTest() {
		assertEquals(maxGuestFilter.getMaxGuests(), 10);
	}
	
	
	
	
	
	

}
