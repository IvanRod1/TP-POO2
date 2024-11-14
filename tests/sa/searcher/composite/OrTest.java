package sa.searcher.composite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.searcher.composite.Or;
import sa.searcher.simpleQuery.City;
import sa.searcher.simpleQuery.IQuery;
import sa.searcher.simpleQuery.MaxGuest;
import sa.searcher.simpleQuery.MaxPrice;

class OrTest {

	private Or orTest;
	private Or orTest2;
	
	private IQuery query1;
	private IQuery query2;
	
	private Booking bookingTarget1;
	private Booking bookingTarget2;
	private Booking bookingTarget3;
	
	private ArrayList<Booking> aux;
	
	private City cityFilter;
	private MaxGuest guestFilter;
	private MaxPrice priceFilter;
	
	@BeforeEach
	void setUp() throws Exception {
		
		query1 = mock(IQuery.class);
		query2 = mock(IQuery.class);
		 
		bookingTarget1 = mock(Booking.class);
		bookingTarget2 = mock(Booking.class);
		bookingTarget3 = mock(Booking.class);
		
		when(bookingTarget1.getCity()).thenReturn("Cordoba");
		when(bookingTarget2.getCity()).thenReturn("Santa Fe");
		when(bookingTarget3.getCity()).thenReturn("Corrientes");
		
		LocalDate fechaAux1 = LocalDate.of(2024, 10, 10);
		LocalDate fechaAux2 = LocalDate.of(2024, 9, 12);
		LocalDate fechaAux3 = LocalDate.of(2024, 12, 20);
		
		when(bookingTarget1.getCheckIn()).thenReturn(fechaAux1);
		when(bookingTarget2.getCheckIn()).thenReturn(fechaAux2);
		when(bookingTarget3.getCheckIn()).thenReturn(fechaAux3);
		
		when(bookingTarget1.price(bookingTarget1.getCheckIn())).thenReturn((double) 15000);
		when(bookingTarget2.price(bookingTarget2.getCheckIn())).thenReturn((double) 17000);
		when(bookingTarget3.price(bookingTarget3.getCheckIn())).thenReturn((double) 20000);
		
		when(bookingTarget1.getMaxGuest()).thenReturn(5);
		when(bookingTarget2.getMaxGuest()).thenReturn(4);
		when(bookingTarget3.getMaxGuest()).thenReturn(10);
		
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		aux.add(bookingTarget2);
		aux.add(bookingTarget1);
		aux.add(bookingTarget3);
		
		cityFilter = mock(City.class);
		guestFilter = mock(MaxGuest.class);
		priceFilter = mock(MaxPrice.class);
		
		
		
		when(cityFilter.getCity()).thenReturn("Formosa");
		when(guestFilter.getMaxGuests()).thenReturn(3);
		when(priceFilter.getValue()).thenReturn(18000);
		
		
		//when(orTest.search(aux)).thenReturn(new ArrayList<>(Arrays.asList(bookingTarget1,bookingTarget2)));
		//when(orTest2.search(aux)).thenReturn(new ArrayList<>(Arrays.asList(bookingTarget1,bookingTarget2)));
	}

	
	@Test
	void newOrTest() {
		
		orTest = new Or(query1,query2);
		
		assertNotNull(orTest);
	}
	
	@Test
	void orSearchTest() {
		
		orTest = new Or(cityFilter,priceFilter);
		
		when(orTest.search(aux)).thenReturn(new ArrayList<>(Arrays.asList(bookingTarget1,bookingTarget2)));
		assertEquals(orTest.search(aux).size(), 2);
		
		
	}
	
	@Test
	void doubleOrTest() {
		
		Or filterAux = new Or(cityFilter,priceFilter);
		
		orTest2 = new Or(guestFilter,filterAux);
		
		when(orTest2.search(aux)).thenReturn(new ArrayList<>(Arrays.asList(bookingTarget1,bookingTarget2)));

		assertEquals(orTest2.search(aux).size(),2);
		
		
		
	}
	
	

}
