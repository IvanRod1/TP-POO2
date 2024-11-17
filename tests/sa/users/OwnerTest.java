package sa.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.accomodationsite.AccomodationSite;
import sa.booking.Booking;
import sa.propertyRent.PropertyRent;
import sa.states.ReservePending;

class OwnerTest {

	private Owner ownerTest;
	private AccomodationSite site;
	
	private PropertyRent duplex;
	private Booking duplexBooking;
	
	@BeforeEach
	void setUp() throws Exception {
		
		site = mock(AccomodationSite.class);
		ownerTest = new Owner("Jefe",23,"correo",site);
		
		duplex = mock(PropertyRent.class);
		duplexBooking = mock(Booking.class);
	}

	@Test
	void newOwnerTest() {
		assertNotNull(ownerTest);
	}
	@Test
	void approveReserveTest() {
		List<Booking> aux = new ArrayList<Booking>();
		aux.add(duplexBooking);
		
		ReservePending reserve = mock(ReservePending.class);
		
		when(duplex.getBookings()).thenReturn(aux);
		when(duplexBooking.getState()).thenReturn(reserve);
		
		when(duplex.getBookings()).thenReturn(Arrays.asList(duplexBooking));
		
		//when(duplex.getBookings().contains(duplexBooking)).thenReturn(true);
		
		ownerTest.approveReserve(duplex,duplexBooking);
		verify(duplexBooking).getState();
	}
	@Test
	void dismissReserveTest() {
		List<Booking> aux = new ArrayList<Booking>();
		aux.add(duplexBooking);
		
		ReservePending reserve = mock(ReservePending.class);
		
		when(duplex.getBookings()).thenReturn(aux);
		when(duplexBooking.getState()).thenReturn(reserve);
		
		when(duplex.getBookings()).thenReturn(Arrays.asList(duplexBooking));
		
		//when(duplex.getBookings().contains(duplexBooking)).thenReturn(true);
		
		ownerTest.dismissReserve(duplex,duplexBooking);
		verify(duplexBooking).getState();
		
		
		
	}
	@Test
	void updatePriceTest() {
		
		ownerTest.updateNormalPrice(duplex,20.0);
	}
	
	

}
