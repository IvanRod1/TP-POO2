package simpleQuery;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class CityTest {
	
	private City filterCity;
	@BeforeEach
	void setUp() throws Exception {
		filterCity = new City("Ciudad");

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
	void getBookingsOfTheCityTest() {
		assertEquals(filterCity.getBookings().size, 0);
	}
	
	@Test
	void addBookingTest() {
		filterCity.addBooking();
		assertEquals(filterCity.getBookings().size, 1);

	}
	
	
	@Test
	void searchTest() {
		assertEquals(filterCity.search(filterCity.getBookings()).size(),0);
	}
	
	//https://app.diagrams.net/#G13TvpcHyre5ERkbsvbK3zEDtO_prz292d#%7B%22pageId%22%3A%22IJnjRaB4pmjr1oVIuCE5%22%7D

}
