package CompositeQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simpleQuery.Booking;
import simpleQuery.City;
import simpleQuery.IQuery;
import simpleQuery.MaxGuest;
import simpleQuery.MaxPrice;

class OrTest {

	private Or orTest;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void newOrTest() {
		IQuery query1 = mock(IQuery.class);
		IQuery query2 = mock(IQuery.class);

		orTest = new Or(query1,query2);
	}
	
	@Test
	void orSearchTest() {
		Booking bookingTarget1 = mock(Booking.class);
		Booking bookingTarget2 = mock(Booking.class);
		Booking bookingTarget3 = mock(Booking.class);
		
		when(bookingTarget1.getCity()).thenReturn("Cordoba");
		when(bookingTarget2.getCity()).thenReturn("Santa Fe");
		when(bookingTarget3.getCity()).thenReturn("Corrientes");
		
		when(bookingTarget1.getValue()).thenReturn(18000);
		when(bookingTarget2.getValue()).thenReturn(15000);
		when(bookingTarget3.getValue()).thenReturn(19000);
		
		
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		City cityFilter = new City("Santa Fe");
		MaxPrice priceFilter = new MaxPrice(20000);
		
		aux.add(bookingTarget2);
		aux.add(bookingTarget1);
		aux.add(bookingTarget3);
		
		orTest = new Or(cityFilter,priceFilter);
		
		assertEquals(orTest.search(aux).size(), 3);
		
		
	}
	
	@Test
	void doubleOrTest() {
		Booking bookingTarget1 = mock(Booking.class);
		Booking bookingTarget2 = mock(Booking.class);
		Booking bookingTarget3 = mock(Booking.class);
		
		when(bookingTarget1.getCity()).thenReturn("Cordoba");
		when(bookingTarget2.getCity()).thenReturn("Santa Fe");
		when(bookingTarget3.getCity()).thenReturn("Corrientes");
		
		when(bookingTarget1.getValue()).thenReturn(18000);
		when(bookingTarget2.getValue()).thenReturn(15000);
		when(bookingTarget3.getValue()).thenReturn(19000);
		
		when(bookingTarget1.getMaxGuest()).thenReturn(5);
		when(bookingTarget2.getMaxGuest()).thenReturn(4);
		when(bookingTarget3.getMaxGuest()).thenReturn(10);
		
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		aux.add(bookingTarget2);
		aux.add(bookingTarget1);
		aux.add(bookingTarget3);
		
		City filterCity = new City("Formosa");
		MaxPrice filterPrice = new MaxPrice(20000);
		MaxGuest filterGuest = new MaxGuest(8);
		
		Or filter2 = new Or(filterCity,filterPrice);
		
		orTest = new Or(filterGuest,filter2);
		
		assertEquals(orTest.search(aux).size(),1);
		
		
		
	}
	
	

}
