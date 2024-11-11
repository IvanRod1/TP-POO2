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
		 * tengo una lista de usuarios, son 2 listas, una de tenant y otra de owner, y tengo que buscar el maximo de inquilinos
		 * que mas hayan alquilado, usando historyBookings, y busco el maximo inquilino que alquilo mas veces, le voy preguntando 
		 * a cada inquilino, le pregunto el historial y la cant de bookings de ese historial y comparo esos numeros, y de ahi te tira 
		 * los maximos 10. 
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
		 * recorro la lista de bookings le pregunto su estado, si es available lo guardo en una lista, es la lista de bookings, 
		 * pregunto si el actual esta disponible, le pido el property y guardo a la propery en una lista que es la que voy a devolver
		 * 
		 * 
		 * */
		
		return this.getAccomodationSite().getVacantProperties().stream()
															   .map(actualBooking -> actualBooking.getProperty())
															   .toList();
										
	}
	
	public double occupancyRate() {
		
		/**
		 * agarro la lista de bookings y le pregunto el estado, si el estado es approve, entonces voy a sumar 1, 
		 * aca tengo que ver el total de bookings de la pagina, ver la cantidad que estan alquilados, y saco el promedio
		 * y despues lo paso a porcentaje, y así digo el porcentaje de ocupación, ej, 10 de 100 10% ocupado.
		 * 
		 * 
		 * */
		
		if (this.getAccomodationSite().getBookings().size() == 0) {
	        return 0.0; 
	    }
		
		return ((double) this.getAccomodationSite().getApprovedBookings().size() /
				 this.getAccomodationSite().getBookings().size()) * 100;
		
	}
	
}




















