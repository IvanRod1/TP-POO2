package sa.searcher.simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import sa.booking.Booking;
import sa.searcher.simpleQuery.City;

class CityTest {
	
	private City filterCity;
	private Booking bookingTest;
	
	private ArrayList<Booking> aux;
	
	@BeforeEach
	void setUp() throws Exception {
		filterCity = new City("Ciudad");
		bookingTest = mock(Booking.class);
		
		aux = new ArrayList<Booking>();
		
		when(bookingTest.getCity()).thenReturn("Varela");
		aux.add(bookingTest);
		
	}

	@Test
	void newFilterCityTest() {
		filterCity = new City("Ciudad");
	}
	
	@Test
	void getCityFilterTest() {
		assertEquals("Ciudad", filterCity.getCity());
	}
	
	@Test
	void setCityFilterTest() {
		filterCity.setCity("Quilmes");
		assertEquals("Quilmes", filterCity.getCity());
	}
	
	
	
	@Test
	void searchTest() {
				
		filterCity.setCity("Varela");
				
		assertEquals(filterCity.search(aux).size(),1);
		
	}
	
	
}
