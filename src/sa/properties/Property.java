package sa.properties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import net.bytebuddy.asm.Advice.This;
import sa.users.Owner;

public class Property implements Rankeable {
	
	private double area;
	private String country;
	private String city;
	private String address;
	private int maxGuests;
	private String description;
	private int maxPicture;
	private List<LocalDate> bookedDays;
	private List<AmenityType> amenities;
	private PropertyType type;
	private List<Picture> pictures;
	private Owner owner;
	private List<Review> reviews;
	
	
	public Property(double area, String country, String city, String address, int maxGuess, String description, int maxPicture,
				   PropertyType type, Owner owner) {
		this.area = area;
		this.country = country;
		this.city = city;
		this.address = address;
		this.maxGuests = maxGuess;
		this.description = description;
		this.maxPicture = maxPicture;
		this.type = type;
		this.owner = owner;
		this.amenities = new ArrayList<AmenityType>();
		this.bookedDays = new ArrayList<LocalDate>();
		this.pictures = new ArrayList<Picture>();
		this.reviews = new ArrayList<Review>();
		
	}

	public void summary() {
		
		// TODO Auto-generated method stub
		/**
		 * imprimir en pantalla cada atributo de property, con un sout para cada uno o buscar una manera para imprimir todos, en cualquier formato
		 * */
		System.out.println("Property Summary:");
	    System.out.println("Area: " + this.area);
	    System.out.println("Country: " + this.country);
	    System.out.println("City: " + this.city);
	    System.out.println("Address: " + this.address);
	    System.out.println("Max Guests: " + this.maxGuests);
	    System.out.println("Description: " + this.description);
	    System.out.println("Max Pictures: " + this.maxPicture);
	    System.out.println("Type: " + this.type);
	    System.out.println("Owner: " + this.owner);
	    System.out.println("Amenities: " + this.amenities);
	    System.out.println("Booked Days: " + this.bookedDays);
	    System.out.println("Pictures: " + this.pictures);
	    System.out.println("Reviews: " + this.reviews);
		
		
	}
	
	public String getCity() {
		return this.city;
	}
	
	public List<Review> getReviews() {
		return this.reviews;
	}
	
	@Override
	public double getRank() {
		/**
		 * calcula el promedio del rating de todos los reviews, que son las vistas que tiene una propiedad y el rating con la que 
		 * la valoró cada usuario en el sitio
		 * 
		 * */
		return this.getReviews().stream()
								.mapToInt(rewiew -> rewiew.getRating())
								.average()
				                .orElse(0.0);
	}

	public int getMaxGuests() {
		//Lo agrego Ivan
		return this.maxGuests;
	}

	public Object getAmenities() {
		return this.amenities;
	}


	public PropertyType getPropertyType() {
		return this.type;
	}
	
 
}
























































