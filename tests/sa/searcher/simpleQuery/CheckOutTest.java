package sa.searcher.simpleQuery;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.searcher.simpleQuery.CheckOut;

class CheckOutTest {

	private CheckOut checkoutTest;
	private Booking bookingTest;
	
	private ArrayList<Booking> aux;
	
	
	@BeforeEach
	void setUp() throws Exception {
		bookingTest = mock(Booking.class);
		checkoutTest = new CheckOut(LocalDate.now());
		
		aux = new ArrayList<Booking>();
		
		when(bookingTest.getCheckOut()).thenReturn(LocalDate.of(2024, 3, 9));
		aux.add(bookingTest);
	}

	@Test
	void newCheckOutTest() {
		CheckOut checkout = new CheckOut(LocalDate.now());
	}
	
	@Test
	void getCheckOutDate() {
		assertEquals(checkoutTest.getDate(),LocalDate.now());
	}
	
	@Test
	void setCheckOutDate() {
		LocalDate fecha = LocalDate.of(2024, 3, 10);
		checkoutTest.setDate(fecha);
		assertEquals(checkoutTest.getDate(),fecha);
	}
	
	@Test
	void checkOutSearchTest() {
		
		
		checkoutTest.setDate(LocalDate.of(2024, 3, 15));
		
		assertEquals(checkoutTest.search(aux).size(),0);
		
		
		
		
	}

}
