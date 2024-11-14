package sa.properties;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.users.Owner;

class PropertyTest {

	private Property property;
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


	@BeforeEach
	void setUp() throws Exception {

	
	this.property = new Property(area, country, city, address, maxGuests, description, maxPicture, type, owner);
	}

	@Test
	void testSummary() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCity() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetRank() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetMaxGuests() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetAmenities() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetPropertyType() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetOwner() {
		fail("Not yet implemented");
	}
	
}
