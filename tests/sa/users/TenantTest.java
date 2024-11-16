package sa.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.accomodationsite.AccomodationSite;
import sa.booking.Booking;
import sa.paymentMethod.PaymentMethod;
import sa.propertyRent.PropertyRent;

class TenantTest {

	private Tenant userTenant;
	private Tenant spyTenant;
	private AccomodationSite site;
	
	private PropertyRent rent;
	
	@BeforeEach
	void setUp() throws Exception {
		
		site = mock(AccomodationSite.class);
		userTenant = new Tenant("corcho",22,"correo",site);
		spyTenant = spy(userTenant);
		
		rent = mock(PropertyRent.class);
		
	}

	@Test
	void newTenanTest() {
		 //userTenant = new Tenant("Miu",22,"correo");
		 assertNotNull(userTenant);
	}
	@Test
	void tenantSummaryTest() {
		spyTenant.summary();
		verify(spyTenant).summary();
	}
	@Test
	void getRankTenanTest() {
		when(spyTenant.getRank()).thenReturn(3.2);
		verify(spyTenant).getRank();
	}
	@Test
	void makeReserveTest() {
		PropertyRent aviso = mock(PropertyRent.class);
		PaymentMethod cash = mock(PaymentMethod.class);
		userTenant.makeReserve(aviso,LocalDate.of(2024,1, 10), LocalDate.of(2024,1,17),cash);
	}
	@Test
	void cancelReserveTest() {
		PropertyRent rentMock = mock(PropertyRent.class);
		when(userTenant.registeredSite.getPropertyRent(rent)).thenReturn(rent);
		userTenant.cancelReserve(rentMock); //Este metodo necesita un metodo del propertyRent que todavia no desarrolle
	}
	@Test
	void allBookingsTest() {
		
		when(userTenant.registeredSite.getBookingsOf(userTenant)).thenReturn(new ArrayList<Booking>());
		assertEquals(userTenant.allBookings().size(),0);
	}
	@Test
	void futureBookingsTest() {
		when(userTenant.registeredSite.getBookingsOf(userTenant)).thenReturn(new ArrayList<Booking>());
		assertEquals(userTenant.futureBookings().size(), 0);
	}
	@Test
	void allBookingCitiesTest() {
		when(userTenant.registeredSite.getBookingsOf(userTenant)).thenReturn(new ArrayList<Booking>());
		assertEquals(userTenant.allBookingsCities().size(),0);
	}

}
