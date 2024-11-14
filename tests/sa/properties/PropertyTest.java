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
	void testConstructor() {
		assertNotNull(property);
	}

	@Test
	void testSummary() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCity() {
		assertEquals(city, property.getCity());
	}
	
	@Test
	void testGetRank() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetMaxGuests() {
		assertEquals(maxGuests, property.getMaxGuests());
	}
	
	@Test
	void testGetAmenities() {
		assertEquals(amenities, property.getAmenities());
	}
	
	@Test
	void testGetPropertyType() {
		assertEquals(type, property.getPropertyType());
	}
	
	@Test
	void testGetOwner() {
		assertEquals(owner, property.getOwner());
	}
	
}
