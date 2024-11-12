package sa.accomodationsite;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DynamicTest;

import sa.properties.AmenityEnum;
import sa.properties.AmenityType;
import sa.properties.Property;
import sa.properties.PropertyEnum;
import sa.properties.PropertyType;
import sa.properties.Rankeable;
import sa.users.Owner;
import sa.users.Tenant;
import sa.users.User;

public class Administrator extends User {
	
	private AccomodationSite accomodationSite;
	
	public Administrator(String fullName, int telephone, String mail, AccomodationSite accomodationSite) {
	
		super(fullName, telephone, mail);
		
		this.accomodationSite = accomodationSite;
	}
	
	public AccomodationSite getAccomodationSite() {
		return this.accomodationSite;
	}
	

	@Override
	public void summary() {
		// TODO Auto-generated method stub
		/**
		 * aca queda vacío 
		 * */
		
	}
	
	public void create(Rankeable rankeable) {
		
		/**
		 * crea un user o una property new Tenant o new Property 
		 * consultar como usar el parametro rankeable
		 * 
		 * */
		Owner newOwner = new Owner("Nacho", 123, "foo@gmail.com");
		PropertyType propertyType = new PropertyType("casa", "con pileta");
		
		Property newProperty = new Property(500.0, "Argentina", "Córdoba", "Carlos Paz 123", 12, 
											"Casa frente al dique", 20, propertyType, newOwner);
		
		Tenant newTenant = new Tenant("Nacho", 456, "bar@gmail.com");
		
	}
	
	public void addAllowedPropertyTypes(PropertyType allowedProperty) {
		
		/**
		 * 
		 * da de alta los tipos de inmuebles, le delega al accomodationSite y el accomodation site chekea 
		 * si es un tipo valido dentro de sus tipos de propiedades habilitados.
		 * 
		 * 
		 * */
		
		this.getAccomodationSite().setAllowedProperties(allowedProperty);
	}
	
	public void allowedAmenities(AmenityType allowedAmenity) {
		/**
		 * 
		 * da de alta los tipos de inmuebles, le delega al accomodationSite y el accomodation site chekea 
		 * si es un tipo valido dentro de sus tipos de propiedades habilitados.
		 *  
		 * */
		this.getAccomodationSite().setAllowedAmenities(allowedAmenity);
		
	}
	
	public List<Tenant> bestTenants(List<Tenant> tenants) {
		
		/**
		 * recibe una lista de tenants por parametro, y la ordena entre los primeros 10 mejores tenants que 
		 * alquilaron mas veces en el sitio. 
		 * 
		 * 
		 * */
		
		return 	tenants.stream()
			    	   .sorted((tenant1, tenant2) -> Integer.compare(
			    				this.getAccomodationSite().bookingHistory(tenant2).size(),
			    				this.getAccomodationSite().bookingHistory(tenant1).size()
			    			)) 
			    	   .limit(10) 
			    	   .toList(); 
		
		
		
	}
	
	public List<Property> propertiesToBeReserve() {
		/**
		 * primero filtra la lista y se queda con los bookings disponibles, y luego la transforma en una lista de propiedades disponibles
		 * 
		 * 
		 * */
		
		return this.getAccomodationSite().getVacantProperties().stream()
															   .map(actualBooking -> actualBooking.getProperty())
															   .toList();
										
	}
	
	public double occupancyRate() {
		
		/**
		 * toma la lista de bookings, primero verifica que no sea cero la cantidad de elementos, porque no se puede 
		 * dividir por cero, luego toma la cantidad de bookings reservados y los divide por el total de bookings de 
		 * la pagina
		 * 
		 * */
		
		if (this.getAccomodationSite().getBookings().size() == 0) {
	        return 0.0; 
	    }
		
		return ((double) this.getAccomodationSite().getApprovedBookings().size() /
				 this.getAccomodationSite().getBookings().size()) * 100;
		
	}
	
}




















