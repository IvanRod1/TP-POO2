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
import sa.properties.Property;
import sa.searcher.simpleQuery.City;

class CityTest {
	
	private City filterCity;
	private Booking bookingTest;
	private Property property;
	private ArrayList<Booking> aux;
	
	@BeforeEach
	void setUp() throws Exception {
		filterCity = new City("Ciudad");
		bookingTest = mock(Booking.class);
		property = mock(Property.class);
		
		aux = new ArrayList<Booking>();
		
		when(this.bookingTest.getProperty()).thenReturn(property);
		when(property.getCity()).thenReturn("Varela");
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
