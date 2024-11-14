package sa.searcher.composite;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.searcher.composite.And;
import sa.searcher.simpleQuery.City;
import sa.searcher.simpleQuery.IQuery;
import sa.searcher.simpleQuery.MaxGuest;
import sa.searcher.simpleQuery.MaxPrice;

class AndTest {

	private And andFilter;
	private And andFilter2;
	
	private IQuery mock1;
	private IQuery mock2;
	
	private Booking bookingTarget1;
	private Booking bookingTarget2;
	private Booking bookingNoTarget;
	
	private City filtroCity;
	private MaxGuest filtroGuest;
	private MaxPrice filtroPrice;
	
	private ArrayList<Booking> aux;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		//DOC'S
		mock1 = mock(IQuery.class);
		mock2 = mock(IQuery.class);
		 
		bookingTarget1 = mock(Booking.class);
		bookingTarget2 = mock(Booking.class);
		bookingNoTarget = mock(Booking.class);
		 
		when(bookingTarget1.getCity()).thenReturn("Cordoba");
		when(bookingTarget2.getCity()).thenReturn("Cordoba");
		when(bookingNoTarget.getCity()).thenReturn("Corrientes");
			
		when(bookingTarget1.getMaxGuest()).thenReturn(2);
		when(bookingTarget2.getMaxGuest()).thenReturn(4);
		when(bookingNoTarget.getMaxGuest()).thenReturn(8);
		
		LocalDate fechaAux1 = LocalDate.of(2024, 10, 10);
		LocalDate fechaAux2 = LocalDate.of(2024, 9, 12);
		LocalDate fechaAux3 = LocalDate.of(2024, 12, 20);
		
		when(bookingTarget1.getCheckIn()).thenReturn(fechaAux1);
		when(bookingTarget2.getCheckIn()).thenReturn(fechaAux2);
		when(bookingNoTarget.getCheckIn()).thenReturn(fechaAux3);
		
		when(bookingTarget1.price(bookingTarget1.getCheckIn())).thenReturn((double) 15000);
		when(bookingTarget2.price(bookingTarget2.getCheckIn())).thenReturn((double) 17000);
		when(bookingNoTarget.price(bookingNoTarget.getCheckIn())).thenReturn((double) 20000);
		
		filtroCity = mock(City.class);
		filtroGuest = mock(MaxGuest.class);
		filtroPrice = mock(MaxPrice.class);
		
		when(filtroCity.getCity()).thenReturn("Cordoba");
		when(filtroGuest.getMaxGuests()).thenReturn(3);
		when(filtroPrice.getValue()).thenReturn(18000);
		
        aux = new ArrayList<Booking>();
		
		aux.add(bookingNoTarget);
		aux.add(bookingTarget1);
		aux.add(bookingTarget2);
		
		andFilter = new And(filtroCity,filtroGuest);
		
		when(andFilter.search(aux)).thenReturn(new ArrayList<>(Arrays.asList(bookingTarget2)));

		
	}

	@Test
	void newAndFilter() {
		

		And filter = new And(mock1,mock2);
		assertNotNull(filter);
	}
	
	@Test
	void andSearchFilterTest() {
		
		assertEquals(andFilter.search(aux).size(),1);
			
	}
	
	@Test
	void doubleAndTest() {
		
		andFilter2 = new And(filtroPrice,filtroCity);
		
		And filterAux = new And(filtroGuest,andFilter2);
		
		
		assertEquals(filterAux.search(aux).size(),1);
			
		
	}
		
	

		
		

		
		
		
		




		
		
	}


