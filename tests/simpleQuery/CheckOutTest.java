package simpleQuery;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckOutTest {

	private CheckOut checkoutTest;
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
		bookingTest = new Booking();
		checkoutTest = new CheckOut();
	}

	@Test
	void newCheckOutTest() {
		CheckOut checkout = new CheckOut();
	}
	
	@Test
	void getCheckOutDate() {
		assertEquals(checkoutTest.getDate(),null);
	}
	
	@Test
	void setCheckOutDate() {
		LocalDate fecha = LocalDate.of(2024, 3, 10);
		checkoutTest.setDate(fecha);
		assertEquals(checkoutTest.getDate(),fecha);
	}
	
	@Test
	void checkOutSearchTest() {
		Booking bookingSpy = spy(bookingTest);
		CheckOut checkOutSpy = spy(checkoutTest);
		ArrayList<Booking> aux = new ArrayList<Booking>();
		
		when(bookingSpy.getCheckOut()).thenReturn(LocalDate.of(2024, 3, 9));
		//when(checkOutSpy.getDate()).thenReturn(LocalDate.of(2024, 3, 11));
		checkOutSpy.setDate(LocalDate.of(2024, 3, 15));
		
		aux.add(bookingSpy);
		
		assertEquals(checkOutSpy.search(aux).size(),0);
		//System.out.println(checkOutSpy.getDate());
		//System.out.print(bookingSpy.getCheckOut());
		
		
		
		
	}

}
