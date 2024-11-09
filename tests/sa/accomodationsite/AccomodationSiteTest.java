package sa.accomodationsite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.accomodationsite.AccomodationSite;
import sa.accomodationsite.Booking;
import sa.accomodationsite.ReserveAvailable;
import sa.accomodationsite.ReserveState;
import sa.properties.PaymentMethodEnum;
import sa.properties.Property;
import sa.users.Tenant;

public class AccomodationSiteTest {
	
	// setUp:
	
	Property property;
	LocalDate checkIn;
	LocalDate checkOut;
	double pricePerDayWeekday;
	double pricePerDayHighSeason;
	double pricePerDayLongSeason;

	Tenant tenant;
	Tenant tenant2;
	
	ReserveState state1;
	ReserveState state2;
	List<Booking> bookings;
	Set<String> cities;
	Booking genericBooking;
	Booking anotherBooking;
	
	Booking lookedBooking;
	
	List<Booking> approvedBookings;
	List<Booking> availableBookings;
	
	// SUT:
	
	AccomodationSite accomodationSite;
	
	// Excercise:
	
	@BeforeEach
	public void setUp() throws Exception {
		
		property = mock(Property.class);
		checkIn = LocalDate.now().plusDays(1);
		checkOut = mock(LocalDate.class);
		pricePerDayWeekday = 100.0;
		pricePerDayHighSeason = 70.0;
		pricePerDayLongSeason = 50.0;
		tenant = mock(Tenant.class);
		tenant2 = mock(Tenant.class);
		state1 =  mock(ReserveAvailable.class);
		state2 = mock(ReserveApproved.class);
		
		/**
		 *  Asigno los mock Stubs acá arriba para que las variables no entren como null en el sut,
			sino que se les asigne el valor esperado para testearlas, en este caso para que no entren al genericBooking como null, 
			pero no directamente al SUT. NO BORRAR.
		
		*/
		
		when(property.getCity()).thenReturn("Buenos Aires");
		
		accomodationSite = new AccomodationSite();
		accomodationSite.createBooking(property, checkIn, checkOut, pricePerDayWeekday, pricePerDayHighSeason, pricePerDayLongSeason, state1, tenant); 
		accomodationSite.createBooking(property, checkIn, checkOut, pricePerDayWeekday, pricePerDayHighSeason, pricePerDayLongSeason, state2, tenant2); 
		
		availableBookings = new ArrayList<Booking>();
		availableBookings.add(accomodationSite.getBookings().getFirst());
		
		bookings = new ArrayList<Booking>();
		bookings.add(accomodationSite.bookingHistory(tenant).get(0));
		
		approvedBookings = new ArrayList<Booking>();
		approvedBookings.add(accomodationSite.getBookings().get(1));
		
		cities = new HashSet<String>();
		cities.add(property.getCity());

		
	}
	
	// Verify:

	@Test
	void cantidadDeBookingsTest() {
		
		
		/**
		 *TODO:
		 * //que pasa si un usuario que no esta registrado crea un booking? test:
		 * idea: este metodo tambien puede agregar al Owner de la propiedad a la lista de Owners
		 * */
		
		
		
		assertEquals(accomodationSite.getBookings().size(), 2);
		
	}
	
	
		/***
		 *TODO:
		AccomodationSite = new AccomodationSite()
		
		assertTrue(accomodationSite.userOfBookingExists(user)));
		
		
	}*/

		


	@Test
	void viewBookingTest() {
		
		/**
		 * esta linea, la 104, meti al booking de esa manera para poder testear este metodo, porque no encontre una manera 
		 * de usar el metodo createBooking para buscar ese booking creado en la lista de bookings.
		 * */
			//accomodationSite.getBookings().add(lookedBooking);
				
			//assertEquals(accomodationSite.viewProperty(lookedBooking), lookedProperty);
		

		//TODO: no sé como testear este metodo
		
		}
	
	@Test
	void getVacantPropertiesTest() {
		
		
		//assertEquals(vacantProperties.size(), 1);
		//assertEquals(accomodationSite.getVacantProperties().size(), 0);
		//accomodationSite.createBooking(property, checkIn, checkOut, pricePerDayWeekday, pricePerDayHighSeason, pricePerDayLongSeason, state1, tenant); 
		
		assertEquals(accomodationSite.getVacantProperties(), availableBookings);
		
		}
		
	@Test
	void bookingHistoryTest() {
		
		assertEquals(accomodationSite.bookingHistory(tenant), bookings);
	}
	
	@Test
	void futureBookings() {
		
		assertEquals(accomodationSite.futureBookings(tenant), bookings);
	}
	
	@Test
	void allBookingsCities() {
		
		//assertEquals(accomodationSite.allBookingCities(tenant).size(), 1);
		
		assertEquals(accomodationSite.allBookingCities(tenant), cities);
	}
	
	@Test
	void getApprovedBookingsTest() {
		assertEquals(accomodationSite.getApprovedBookings(), approvedBookings);
	}

}




















































