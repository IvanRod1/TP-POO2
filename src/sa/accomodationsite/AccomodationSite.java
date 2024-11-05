package sa.accomodationsite;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sa.properies.Property;
import sa.user.Owner;
import sa.user.Tenant;

public class AccomodationSite {
	
	private List<Booking> bookings;
	private List<Tenant> tenants;
	private List<Owner> owners;
	private IQuery searcher;
	
	private Administrator administrator;
	
	public AccomodationSite() {
		this.bookings = new ArrayList<Booking>();
		this.tenants = new ArrayList<Tenant>();
		this.owners = new ArrayList<Owner>();
		this.searcher = new Searcher();
	}
	
	public void createBooking(Property property, LocalDate checkIn, LocalDate checkOut,
							  double pricePerDayWeekday, double pricePerDayHighSeason, double pricePerDayLongSeason, ReserveState state, Tenant tenant) {
		
		Booking bookingACrear = new Booking(property, checkIn, checkOut, pricePerDayWeekday, 
											pricePerDayHighSeason, pricePerDayLongSeason, state, tenant);
		
		this.bookings.add(bookingACrear);
	}
	
	public void eliminateBooking(Booking booking) {
		
		this.bookings.remove(booking);
	}
	
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}
	
	public void viewProperty(Booking booking) {
		
		 booking.getProperty().summary();
		 /*
		  * el sumario es un print screen ln para simular la visualizacion de la propiedad
		  * */
		 /*
			 * creo un booking mock, el sumario sabe respopnder los atributos de property, 
			 * y le hago en el stub un when que retorne un property mmock con valores que le agrego 
			 * que serian los atributos de la property.
			 * mock de propiedad stub y mock de booking, hago set property y le paso el property mock 
			 * es un void
			 * */
		 
	}
	
	public List<Booking> getVacantProperties() {
		
		return this.getBookings().stream()
	               .filter(bookingActual -> bookingActual.getState() instanceof ReserveAvailable)
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
								 .filter(bookingActual -> bookingActual.getTenant().equals(tenant))
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
						  	  		      .filter(bookingActual -> bookingActual.getCheckIn().isAfter(today))
						  	  		      .toList();
		
	}
	
	public Set<String> allBookingCities(Tenant tenant) {
		
		return this.bookingHistory(tenant).stream()
								 		  .map(bookingActual -> bookingActual.getProperty().getCity())
								 		  .collect(Collectors.toSet());
		
		
		
		
		/**
		 * agarro la lista dada por bookingHistory, primero creo una lista auxiliar de los bookings ya alquilados historicamente, y le guardo 
		 * todas las ciudades en donde alquilo el inquilino, de esta lista de bookings 
		 * saco todas las ciudades, por cada booking le pido la ciudad y la guardo en un set. y retorno ese set. 
		 * el set buscar como pasarlo a lista y viceversa.
		 * 
		 * */
		
		
	}
	
	public List<Booking> searcher(String city, LocalDate checkIn, LocalDate checkOut) {
		return bookings;
		/**
		 * voy a tener una lista de bookings, es la del accomodation site, voy a tener que hacer un recorrido sobre 
		 * la lista de bookings de acomodation site y voy a tener que utilizar el composite query AND
		 * 
		 * 
		 * */
		
		/*IQuery filtroCity = new City(city);
		IQuery filtroCheckIn = new ChechIn(LocalDate In);
		IQuery filtroCheckOut = new ChechOut(LocalDate Out);
		
		IQuery firstPartQuery = new And(filtroCity, filtroCheckIn);
		IQuery secondPartQuery = new And(filtroCheckOut, firstPartQuery);
		
		return secondPartQuery.search(this.getBookings());*/
		
		
	}
	
	public void cancelBooking(Booking booking) {
		
		/**
		 * tengo que hacer que el estado de cada booking entienda el mensajke cancelBoking, booking.getState().cancelBooking()
		 * hablarlos con martin el jueves
		 * 
		 * */
	}
	
	public void reserveBooking(Booking booking) {
		
	}
	
		
}
	
 	
	
	

