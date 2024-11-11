package sa.accomodationsite;

import java.time.LocalDate;
//import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//import sa.searcher.*;
import sa.searcher.simpleQuery.IQuery;
import sa.booking.Booking;
import sa.booking.Period;
import sa.booking.ReserveAvailable;
import sa.booking.ReserveApproved;
import sa.properties.AmenityType;
import sa.payments.PaymentMethod;
import sa.properties.Property;
import sa.properties.PropertyType;
import sa.users.Owner;
import sa.users.Tenant;


public class AccomodationSite {
	
	private List<Booking> bookings;
	private List<Tenant> tenants;
	private List<Owner> owners;
	private List<PropertyType> allowedProperties;
	private List<AmenityType> allowedAmenities;
	private IQuery searcher;
	
	private Administrator administrator;
	
	public AccomodationSite() {
		this.bookings = new ArrayList<Booking>();
		this.tenants = new ArrayList<Tenant>();
		this.owners = new ArrayList<Owner>();
	}
	
	public void createBooking(Property property, LocalDate checkIn, LocalDate checkOut, List<PaymentMethod> paymentMethods,
							  double pricePerDayWeekday, List<Period> periods) {
		
		Booking newBooking = new Booking(property, checkIn, checkOut, paymentMethods,
											pricePerDayWeekday, periods);
		
		if(this.verifyPropertyType(property) && this.verifyAmenityType(property)) {
			
			this.bookings.add(newBooking);
		} else {
			throw new RuntimeException("la propiedad o el servicio dados no son válidos para el sitio web");
		}
		
		
	}
	
	
	private boolean verifyAmenityType(Property property) {
		/**
		 * verifica si el tipo de la propiedad dada pertenece al tipo de propiedad aceptado por el sitio web
		 * 
		 * */
		return this.getAllowedAmenities().containsAll((Collection<?>) property.getAmenities());
	}

	private boolean verifyPropertyType(Property property) {
		/**
		 * verifica si el tipo de los servicios de la propiedad dada pertenecen al tipo de servicios aceptado por el sitio web
		 * 
		 * */
		
		return this.getAllowedProperties().stream()
			      						  .anyMatch(propertyType -> propertyType.equals(property.getPropertyType().type()));
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}
	
	public List<PropertyType> getAllowedProperties() {
		return this.allowedProperties;
	}
	
	public List<AmenityType> getAllowedAmenities() {
		return this.allowedAmenities;
	}
	
	public void setAllowedProperties(PropertyType allowedProperty) {
		
		this.getAllowedProperties().add(allowedProperty);
		
		
	}
	
	public void setAllowedAmenities(AmenityType allowedAmenity) {
		
		this.getAllowedAmenities().add(allowedAmenity);
		
	}
	
	
	public void viewProperty(Booking booking) {
		
		 
		 /*
		  * el sumario es un print screen ln para simular la visualizacion de la propiedad
		  * */
		 /*
			 * creo un booking mock, el sumario sabe respopnder los atributos de property, 
			 * y le hago en el stub un when que retorne un property mmock con valores que le agrego 
			 * que serian los atributos de la property.
			 * mock de propiedad stub y mock de booking, hago set property y le paso el property mock 
			 * es un void
			 * 
			 * testear que booking no sea null y con un verify de times(1)
			 * */
		
		booking.getProperty().summary();
		 
	}
	
	public List<Booking> getVacantProperties() {
		
		return this.getBookings().stream()
	               .filter(actualBooking -> actualBooking.getState() instanceof ReserveAvailable)
	               .toList();
	}
	
	public List<Booking> getApprovedBookings() {
		return this.getBookings().stream()
								 .filter(actualBooking -> actualBooking.getState() instanceof ReserveApproved)
								 .toList();
	}
	
	public List<Booking> bookingHistory(Tenant tenant) {
		
		
		
		/*
		 * recorrer la lista de bookins del acomodation, es un recorrido de busqueda, retorna los 
		 * bookings con el inquilino dado por parametro, y si es, los guarda en una lista y los retorna 
		 * en una lista de bookings.
		 * 
		 * */
	
		return this.getBookings().stream()
								 .filter(actualBooking -> actualBooking.getTenant().equals(tenant))
								 .toList();
		
		
	}
	
		
	public List<Booking> futureBookings(Tenant tenant) {
		
		
		/**
		 * futureBookings: bookings que son futuros al dia de hoy, a la fecha actual, siempre el resultado va a cambiar,
		 * la lista que voy a usar es la del metodo anterior, como lista auxiliar, y a esta lista la voy a 
		 * filtrar por fecha de chekIn, comparada a la fecha de hoy, diciendo (chekIn > fechaDeHoy)  
		 * resultado, los bookings futuros.
		 * 
		 * */
		
		LocalDate today = LocalDate.now();
		
		return this.bookingHistory(tenant).stream()
						  	  		      .filter(actualBooking -> actualBooking.getCheckIn().isAfter(today))
						  	  		      .toList();
		
	}
	
	public Set<String> allBookingCities(Tenant tenant) {
		
		return this.bookingHistory(tenant).stream()
								 		  .map(actualBooking -> actualBooking.getProperty().getCity())
								 		  .collect(Collectors.toSet());
		
		
		
		
		/**
		 * agarro la lista dada por bookingHistory, primero creo una lista auxiliar de los bookings ya alquilados historicamente, y le guardo 
		 * todas las ciudades en donde alquilo el inquilino, de esta lista de bookings 
		 * saco todas las ciudades, por cada booking le pido la ciudad y la guardo en un set. y retorno ese set. 
		 * el set buscar como pasarlo a lista y viceversa.
		 * 
		 * */
		
		
	}
	
	public List<Booking> search(IQuery query) {
		/**
		 * voy a tener una lista de bookings, es la del accomodation site, voy a tener que hacer un recorrido sobre 
		 * la lista de bookings de acomodation site y voy a tener que utilizar el composite query AND
		 * 
		 * 
		 * */
		
	//	if (this.searcher == null) {
			/**
			 * si el usuario no establece un criterio de busqueda, se retornan todos los bookings de la pagina.
			 * */
			
		/*	return this.getBookings();
			
		}
			
			this.searcher = query;
			
			return this.searcher.search(this.getBookings());
			
	*/
		return null;
	}
	
	
	
	
	
	


	
		
}
	
 	
	
	

