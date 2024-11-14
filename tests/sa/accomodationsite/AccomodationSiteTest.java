package sa.accomodationsite;

import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.accomodationsite.AccomodationSite;

//import sa.booking.ReserveAvailable;
//import sa.booking.ReserveBooked;
//import sa.booking.IReserveState;
//import sa.booking.SpecialPeriod;
//import sa.booking.ReserveOccupied;
//import sa.payments.PaymentMethod;
//import sa.properties.PaymentMethodEnum;
import sa.booking.Booking;
import sa.booking.IReserveState;
import sa.booking.PaymentMethod;
import sa.booking.ReserveAvailable;
import sa.booking.ReserveBooked;
import sa.booking.ReserveOccupied;
import sa.booking.SpecialPeriod;
import sa.booking.BookedPeriod;
import sa.properties.AmenityType;
import sa.properties.Property;
import sa.properties.PropertyType;
import sa.users.Tenant;

public class AccomodationSiteTest {
	
	// setUp:
	
	Property property;
	
	Property invalidProperty;
		
	LocalDate checkIn;
	LocalDate checkOut;
	
	double pricePerDayWeekday;
	double pricePerDayHighSeason;
	double pricePerDayLongSeason;
	
	Tenant ivan;
	Tenant martin;
	Tenant nacho;
	
	IReserveState reserveAvailable;
	IReserveState reserveBooked;
	IReserveState reserveOccupied;
	
	List<Booking> bookings;
	Set<String> cities;
	
	Booking bookingMock1;
	Booking bookingMock2;
	Booking bookingMock3;
	
	List<Booking> approvedBookings;
	List<Booking> availableBookings;
	
	List<PaymentMethod> paymentsMethods;
	
	PaymentMethod cash;
	
	//------------------
	SpecialPeriod period;
	List<SpecialPeriod> periods;
	//------------------
	 
	
	Administrator administrator;
	
	List<AmenityType> allowedAmenities;
	List<AmenityType> invalidAmenities;
	
	AmenityType electricity;
	AmenityType internet;
	AmenityType TV;
	
	PropertyType house;
	PropertyType depto;
	PropertyType mansion;
	
	// SUT:
	
	AccomodationSite accomodationSite;
	
	// Excercise:
	
	@BeforeEach
	public void setUp() throws Exception {
		
		// inicializacion de constructor de booking
		accomodationSite = new AccomodationSite();
		
		
		checkIn = LocalDate.of(2024, 11, 10);
		checkOut = LocalDate.of(2024, 11, 20);
		
		property = mock(Property.class);
	
		
		
		paymentsMethods = new ArrayList<PaymentMethod>();
		paymentsMethods.add(cash);
		
		pricePerDayWeekday = 1000.0;
		
		periods = new ArrayList<SpecialPeriod>();
		period = mock(SpecialPeriod.class);
		periods.add(period);
		
		house = mock(PropertyType.class);
		depto = mock(PropertyType.class);
		mansion = mock(PropertyType.class);
		
		accomodationSite.addAllowedProperty(depto);
		accomodationSite.addAllowedProperty(house);
		
		internet = mock(AmenityType.class);
		electricity = mock(AmenityType.class);
		TV = mock(AmenityType.class);
		
		accomodationSite.addAllowedAmenities(internet);
		accomodationSite.addAllowedAmenities(electricity);
		
		bookingMock1 = mock(Booking.class);
		bookingMock2 = mock(Booking.class);
		bookingMock3 = mock(Booking.class);
		
		//CAMBIAR EL NOMBRE CUANDO MARTIN HAGA MERGE CON SU RAMA A MAIN
		reserveAvailable = mock(ReserveAvailable.class);
		reserveOccupied = mock(ReserveOccupied.class);
		reserveBooked = mock(ReserveBooked.class);
		
		availableBookings = new ArrayList<Booking>();
		
		ivan = mock(Tenant.class);
		nacho = mock(Tenant.class);
		martin = mock(Tenant.class);	
		
	}
	
	@Test
	void createBookingTest() {
		List<AmenityType> auxProperty = new ArrayList<AmenityType>();
		auxProperty.add(electricity);
		auxProperty.add(internet);
		
		when(property.getPropertyType()).thenReturn(house);
		
		when(property.getAmenities()).thenReturn(auxProperty);
		
		
		accomodationSite.createBooking(property, checkIn, checkOut, paymentsMethods, pricePerDayWeekday, periods);
		
		assertEquals(accomodationSite.getBookings().size(), 1);
		
		
	}
/*	@Test
	void createNonValidBookingTest() {
		//ESTE METODO EXPLOTA POR EL HECHO DE LA EXCEPCION, VERIFICAR COMO SE PUEDE SOLUCIONAR
		List<AmenityType> auxProperty = new ArrayList<AmenityType>();
		auxProperty.add(electricity);
		auxProperty.add(internet);
		
		when(property.getPropertyType()).thenReturn(mansion);
		
		when(property.getAmenities()).thenReturn(auxProperty);
		
        accomodationSite.createBooking(property, checkIn, checkOut, paymentsMethods, pricePerDayWeekday, periods);
		
		assertEquals(accomodationSite.getBookings().size(), 0);
		
		
	}*/
	// Verify:
	
	@Test
	void cantidadDeBookingsTest() {
		
		
		/**
		 *TODO:
		 * //que pasa si un usuario que no esta registrado crea un booking? test:
		 * idea: este metodo tambien puede agregar al Owner de la propiedad a la lista de Owners
		 * */
		
		
		
		assertEquals(accomodationSite.getBookings().size(), 0);
		
	}

	@Test
	void getVacantPropertiesTest() {
		
		
		//assertEquals(vacantProperties.size(), 1);
		//assertEquals(accomodationSite.getVacantProperties().size(), 0);
		//accomodationSite.createBooking(property, checkIn, checkOut, pricePerDayWeekday, pricePerDayHighSeason, pricePerDayLongSeason, state1, tenant); 
		
		when(bookingMock1.getState()).thenReturn(reserveAvailable);
		when(bookingMock2.getState()).thenReturn(reserveAvailable);
		when(bookingMock3.getState()).thenReturn(reserveBooked);
		
		availableBookings.add(bookingMock1);
		availableBookings.add(bookingMock2);
		
		accomodationSite.addBooking(bookingMock1);
		accomodationSite.addBooking(bookingMock2);
		accomodationSite.addBooking(bookingMock3);
		
		assertEquals(accomodationSite.getVacantProperties(), availableBookings);
		
		}
		
	@Test
	void bookingHistoryTest() {
		
		when(bookingMock1.getTenant()).thenReturn(ivan);
		when(bookingMock2.getTenant()).thenReturn(nacho);
		when(bookingMock3.getTenant()).thenReturn(nacho);
		
		accomodationSite.addBooking(bookingMock1);
		accomodationSite.addBooking(bookingMock2);
		accomodationSite.addBooking(bookingMock3);
	
		assertEquals(accomodationSite.bookingHistory(nacho).size(), 2);
		assertEquals(accomodationSite.bookingHistory(ivan).size(), 1);
	}
	
	@Test
	void futureBookings() {
		
		accomodationSite.addBooking(bookingMock1);
		accomodationSite.addBooking(bookingMock2);
		accomodationSite.addBooking(bookingMock3);
		
		when(bookingMock1.getTenant()).thenReturn(ivan);
		when(bookingMock2.getTenant()).thenReturn(nacho);
		when(bookingMock3.getTenant()).thenReturn(nacho);
		
		when(bookingMock1.getCheckIn()).thenReturn(LocalDate.now().plusDays(15));
		when(bookingMock2.getCheckIn()).thenReturn(LocalDate.now().minusMonths(2));
		when(bookingMock3.getCheckIn()).thenReturn(LocalDate.now().plusMonths(4));
		
		assertEquals(accomodationSite.futureBookings(ivan).size(), 1);
		assertEquals(accomodationSite.futureBookings(nacho).size(), 1);
	}
	
	@Test
	void allBookingsCities() {
		
		//assertEquals(accomodationSite.allBookingCities(tenant).size(), 1);
		
		accomodationSite.addBooking(bookingMock1);
		accomodationSite.addBooking(bookingMock2);
		accomodationSite.addBooking(bookingMock3);
		
		Property propertyMock1 = mock(Property.class);
		Property propertyMock2 = mock(Property.class);
		Property propertyMock3 = mock(Property.class);
		
		when(bookingMock1.getTenant()).thenReturn(nacho);
		when(bookingMock2.getTenant()).thenReturn(nacho);
		when(bookingMock3.getTenant()).thenReturn(nacho);
	
		when(propertyMock1.getCity()).thenReturn("Rosario");
		when(propertyMock2.getCity()).thenReturn("Buenos Aires");
		when(propertyMock3.getCity()).thenReturn("Cordoba");
		
		when(bookingMock1.getProperty()).thenReturn(propertyMock1);
		when(bookingMock2.getProperty()).thenReturn(propertyMock2);
		when(bookingMock3.getProperty()).thenReturn(propertyMock3);
		
		assertEquals(accomodationSite.allBookingCities(nacho).size(), 3);
	}
	
	@Test
	void getApprovedBookingsTest() {
		

		accomodationSite.addBooking(bookingMock1);
		accomodationSite.addBooking(bookingMock2);
		accomodationSite.addBooking(bookingMock3);
		
		when(bookingMock1.getState()).thenReturn(reserveAvailable);
		when(bookingMock2.getState()).thenReturn(reserveAvailable);
		when(bookingMock3.getState()).thenReturn(reserveBooked);
		
		assertEquals(accomodationSite.getApprovedBookings().size(),1);
		
	}
	
	
	
	@Test
	void viewPropertyTest() {
		
		accomodationSite.addBooking(bookingMock1);
		
		when(bookingMock1.getProperty()).thenReturn(property);
		
		accomodationSite.viewProperty(bookingMock1);
		
		verify(property).summary();
		
		//verify(accomodationSite, times(1)).viewProperty(bookingMock1);
	}

	
}

/*
//CODIGO DE NACHO 

property = mock(Property.class);
invalidProperty = mock(Property.class);
invalidType = mock(PropertyType.class);

validType = mock(PropertyType.class);
checkIn = LocalDate.now().plusDays(1);
checkOut = mock(LocalDate.class);

pricePerDayWeekday = 100.0;
pricePerDayHighSeason = 70.0;
pricePerDayLongSeason = 50.0;

tenant = mock(Tenant.class);
tenant2 = mock(Tenant.class);

state1 =  mock(ReserveAvailable.class);
state2 = mock(ReserveApproved.class);
state3 = mock(ReserveCompleted.class);

paymentsMethods = new ArrayList<PaymentMethod>();
cash = mock(PaymentMethod.class);
paymentsMethods.add(cash);

periods = new ArrayList<Period>();
period = mock(Period.class);
periods.add(period);

bookingMock = mock(Booking.class);

// inicializacion de valores de referentes al accomodationSite

validAmenity1 = mock(AmenityType.class);
validAmenity2 = mock(AmenityType.class);

propertyType1 = mock(PropertyType.class);
propertyType2 = mock(PropertyType.class);
propertyType3 = mock(PropertyType.class);

// SUT: accomodationSite

accomodationSite = new AccomodationSite();
/*accomodationSite.createBooking(property, checkIn, checkOut, paymentsMethods,
							   pricePerDayWeekday, periods); 
accomodationSite.createBooking(property, checkIn, checkOut, paymentsMethods,
		   					   pricePerDayWeekday, periods); */
/*
availableBookings = new ArrayList<Booking>();
availableBookings.add(accomodationSite.getBookings().getFirst());

bookings = new ArrayList<Booking>();
bookings.add(accomodationSite.bookingHistory(tenant).get(0));

approvedBookings = new ArrayList<Booking>();
approvedBookings.add(accomodationSite.getBookings().get(1));

cities = new HashSet<String>();
cities.add(property.getCity());

allowedAmenities = new ArrayList<AmenityType>();
invalidAmenities = new ArrayList<AmenityType>();

allowedAmenities.add(validAmenity1);
invalidAmenities.add(validAmenity2);

accomodationSite.addAllowedProperty(propertyType1);
accomodationSite.addAllowedProperty(propertyType2);

accomodationSite.addAllowedAmenities(validAmenity1);
accomodationSite.addAllowedAmenities(validAmenity2);



//administrator = spy(Administrator.class);


/**
 *  Asigno los mock Stubs acá arriba para que las variables no entren como null en el sut,
	sino que se les asigne el valor esperado para testearlas, en este caso para que no entren al genericBooking como null, 
	pero no directamente al SUT. NO BORRAR.

*/

/*when(property.getCity()).thenReturn("Buenos Aires");
when(invalidProperty.getPropertyType()).thenReturn(invalidType);
when(invalidType.type()).thenReturn("casa");
when(property.getPropertyType()).thenReturn(validType);
when(validType.type()).thenReturn("duplex");

when(property.getAmenities()).thenReturn(allowedAmenities);
when(invalidProperty.getAmenities()).thenReturn(invalidAmenities);*/



















































