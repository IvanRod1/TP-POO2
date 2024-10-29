package simpleQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class CityTest {
	
	private City filterCity;
	private Booking bookingTest;
	
	@BeforeEach
	void setUp() throws Exception {
		filterCity = new City("Ciudad");
		bookingTest = new Booking();
		
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
		ArrayList<Booking> listaAux = new ArrayList<Booking>();
		Booking bookingSpy = spy(bookingTest);
		City citySpy = spy(filterCity);
		
		when(bookingSpy.getCity()).thenReturn("Varela");
		when(citySpy.getCity()).thenReturn("Varela");
		
		citySpy.setCity("Varela");
		
		listaAux.add(bookingSpy);
		
		assertEquals(citySpy.search(listaAux).size(),1);
		
	}
	
	
}
