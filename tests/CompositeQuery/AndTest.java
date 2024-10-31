package CompositeQuery;
import simpleQuery.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AndTest {

	private And andFilter;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void newAndFilter() {
		IQuery mock1 = mock(IQuery.class);
		IQuery mock2 = mock(IQuery.class);

		And filter = new And(mock1,mock2);
	}
	
	@Test
	void andSearchFilterTest() {
		City cityMock = mock(City.class);
		MaxGuest guestMock = mock(MaxGuest.class);
				
		Booking bookingTarget1 = mock(Booking.class);
		Booking bookingTarget2 = mock(Booking.class);
		Booking bookingNoTarget = mock(Booking.class);
		
		
		
		when(bookingTarget1.getCity()).thenReturn("Cordoba");
		when(bookingTarget2.getCity()).thenReturn("Cordoba");
		when(bookingNoTarget.getCity()).thenReturn("Corrientes");
		
		when(bookingTarget1.getMaxGuest()).thenReturn(5);
		when(bookingTarget2.getMaxGuest()).thenReturn(4);
		when(bookingNoTarget.getMaxGuest()).thenReturn(8);
		
		when(cityMock.getCity()).thenReturn("Cordoba");
		when(guestMock.getMaxGuests()).thenReturn(3);

		//reveer codigo de search
		
		City filtroCity = new City("Cordoba");
		MaxGuest filtroGuest = new MaxGuest(3);
		
		andFilter = new And(filtroCity,filtroGuest);

		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		aux.add(bookingNoTarget);
		aux.add(bookingTarget1);
		aux.add(bookingTarget2);
		
		assertEquals(andFilter.search(aux).size(),2);
		
	}
	
	@Test
	void doubleAndTest() {
		
		Booking bookingTarget1 = mock(Booking.class);
		Booking bookingTarget2 = mock(Booking.class);
		Booking bookingNoTarget = mock(Booking.class);
		
		when(bookingTarget1.getCity()).thenReturn("Cordoba");
		when(bookingTarget2.getCity()).thenReturn("Cordoba");
		when(bookingNoTarget.getCity()).thenReturn("Corrientes");
		
		when(bookingTarget1.getMaxGuest()).thenReturn(5);
		when(bookingTarget2.getMaxGuest()).thenReturn(4);
		when(bookingNoTarget.getMaxGuest()).thenReturn(8);
		
		when(bookingTarget1.getValue()).thenReturn(15000);
		when(bookingTarget2.getValue()).thenReturn(17000);
		when(bookingNoTarget.getValue()).thenReturn(20000);
		
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		aux.add(bookingNoTarget);
		aux.add(bookingTarget1);
		aux.add(bookingTarget2);
		
		
		City filtroCity = new City("Cordoba");
		MaxGuest filtroGuest = new MaxGuest(3);
		MaxPrice filtroPrice = new MaxPrice(18000);
		
		And filter2 = new And(filtroPrice,filtroCity);
		
		andFilter = new And(filtroGuest,filter2);
		assertEquals(filter2.search(aux).size(),2);
		
		
		
		
	}
		
	

		
		

		
		
		
		




		
		
	}


