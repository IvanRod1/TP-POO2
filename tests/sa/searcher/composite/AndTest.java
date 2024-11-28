package sa.searcher.composite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Period;
import sa.booking.Property;
import sa.searcher.simpleQuery.CheckIn;
import sa.searcher.simpleQuery.City;
import sa.searcher.simpleQuery.MaxGuest;
import sa.searcher.simpleQuery.MinPrice;

class AndTest {

	private And querytest1;
	private And querytest2;
	
	private Booking bookingMock;
	private List<Booking> bookings;
	
	
	private City cityQuery;
	private MaxGuest maxGuestQuery;
	private MinPrice minPriceQuery;
	private CheckIn checkInQuery;
	private Property house;
	
	private LocalDate startDate;
	private Period bookingPeriod;
	private LocalDate checkInDate;
	
	
	@BeforeEach
	void setUp() throws Exception {
		cityQuery = mock(City.class);
		maxGuestQuery = mock(MaxGuest.class);
		minPriceQuery = mock(MinPrice.class);
		checkInQuery = mock(CheckIn.class);
		
		querytest1 = new And(cityQuery,maxGuestQuery);
		querytest2 = new And(minPriceQuery,checkInQuery);
		
		bookingMock = mock(Booking.class);
		bookings = new ArrayList<Booking>();
		
		house = mock(Property.class);
		
		bookingPeriod = mock(Period.class);
		startDate = LocalDate.of(2024, 11, 20);
		checkInDate = LocalDate.of(2024, 11, 19);
		
		when(bookingPeriod.start()).thenReturn(startDate); 
		
		when(house.getCity()).thenReturn("Buenos Aires");
		when(house.getMaxGuests()).thenReturn(5);
		
		when(bookingMock.getProperty()).thenReturn(house);
		when(bookingMock.getPeriod()).thenReturn(bookingPeriod);
		when(bookingMock.price(checkInDate)).thenReturn(1239.0);
		
		
		when(cityQuery.getNameCity()).thenReturn("Buenos Aires");
		when(maxGuestQuery.getMaxGuests()).thenReturn(5); 
		
		when(minPriceQuery.getMinPrice()).thenReturn(1238.0); 
		when(checkInQuery.getCheckInDate()).thenReturn(checkInDate);
		
		when(cityQuery.search(bookings)).thenReturn(bookings);
		when(maxGuestQuery.search(bookings)).thenReturn(bookings);
		when(minPriceQuery.search(bookings)).thenReturn(bookings);
		when(checkInQuery.search(bookings)).thenReturn(new ArrayList<Booking>());
		
		
		
		
		bookings.add(bookingMock);
		
		
		
	}

	@Test
	void newAndQueryTest() {
		assertNotNull(querytest1);
		assertNotNull(querytest2);
	} 
	@Test
	void successfulQuerySearchTest() {
		assertEquals(querytest1.search(bookings).size(),1);
	}
	@Test
	void failedQuerySearchTest() {
		assertEquals(querytest2.search(bookings).size(),0);
	}

}
