package sa.accomodationsite;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sa.searcher.simpleQuery.IQuery;
import sa.booking.Booking;
import sa.booking.Period;
import sa.booking.ReserveAvailable;
import sa.booking.ReserveApproved;
import sa.properties.AmenityType;
//import sa.payments.PaymentMethod;
import sa.booking.*;
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
		/**
		 * crea un booking nuevo y lo agrega a la lista de bookings. Antes de agregarlo verifica que la propiedad del booking
		 * tenga un tipo de propiedad valido y unos tipos de servicios validos para sitio web, dados por el administrador.
		 * 
		 * */
		
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
	
	public List<PropertyType> getAllowedProperties() {
		return this.allowedProperties;
	}
	
	public List<AmenityType> getAllowedAmenities() {
		return this.allowedAmenities;
	}
	
	public void setAllowedProperties(PropertyType allowedProperty) {
		
		/**
		 * Agrega el tipo de propiedad valido para el sitio, dado por el administrador
		 * */
		
		this.getAllowedProperties().add(allowedProperty);
		
		
	}
	
	public void setAllowedAmenities(AmenityType allowedAmenity) {
		
		/**
		 * Agrega los tipos de servicios validos para el sitio, dados por el administrador
		 * 
		 * */
		
		this.getAllowedAmenities().add(allowedAmenity);
		
	}
	
	
	public void viewProperty(Booking booking) {
		
		 /*
		  * llama al metodo sumary de property para printear en pantalla los atributos de la propiedad
		  * 
			 * creo un booking mock, el sumario sabe respopnder los atributos de property, 
			 * y le hago en el stub un when que retorne un property mmock con valores que le agrego 
			 * que serian los atributos de la property.
			 * mock de propiedad stub y mock de booking, hago set property y le paso el property mock 
			 * es un void
			 * 
			 * testear que booking no sea null y con un verify de times(1)
			 * 
			 * el sumario es un print screen ln para simular la visualizacion de la propiedad
			 * */
		
		booking.getProperty().summary();
		 
	}
	
	public List<Booking> getVacantProperties() {
		
		/**
		 * filtra la lista de bookings del sitio y se queda con las que tienen el estado disponible.
		 * */
		
		return this.getBookings().stream()
	               .filter(actualBooking -> actualBooking.getState() instanceof ReserveAvailable)
	               .toList();
	}
	
	public List<Booking> getApprovedBookings() {
		/**
		 * filtra la lista de bookings del sitio y se queda con las que tienen el estado aprovado
		 * */
		
		return this.getBookings().stream()
								 .filter(actualBooking -> actualBooking.getState() instanceof ReserveApproved)
								 .toList();
	}
	
	public List<Booking> bookingHistory(Tenant tenant) {
		
		
		
		/*
		 * Recorre la lista de bookings y la filtra por el tenant dado, y retorna una lista de bookings del tenant dado
		 * 
		 * */
	
		return this.getBookings().stream()
								 .filter(actualBooking -> actualBooking.getTenant().equals(tenant))
								 .toList();
		
		
	}
	
		
	public List<Booking> futureBookings(Tenant tenant) {
		
		
		/**
		 * primero filtra la lista de bookings y se queda con todos los alquileres del tenant dado, luego, a esa lista 
		 * resultante, la filtra y se queda con los bookings con un checkIn superior al dia de hoy, es decir, los bookings
		 * futuros
		 * 
		 * */
		
		LocalDate today = LocalDate.now();
		
		return this.bookingHistory(tenant).stream()
						  	  		      .filter(actualBooking -> actualBooking.getCheckIn().isAfter(today))
						  	  		      .toList();
		
	}
	
	public Set<String> allBookingCities(Tenant tenant) {
		/**
		 * primero filtra la lista de bookings y se queda con todos los alquileres del tenant dado, luego transforma esa
		 * lista en una lista de ciudades por las que alquiló el tenant dado, y sin elementos repetidos
		 * */
		
		return this.bookingHistory(tenant).stream()
								 		  .map(actualBooking -> actualBooking.getProperty().getCity())
								 		  .collect(Collectors.toSet());
		
	}
	
	public List<Booking> search(IQuery query) {
		/**
		 * recibe un iquery como parametro, luego verifica si el searcher esta vacio o es null, entonces retorna 
		 * todos los bookings del sitio, y sino, almacena el criterio de busqueda dado.
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
	
 	
	
	

