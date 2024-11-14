package sa.properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
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
	private Review rv1;
	private Review rv2;


	@BeforeEach
	void setUp() throws Exception {

		rv1 = mock(Review.class);
		rv2 = mock(Review.class);
		
		reviews = new ArrayList<Review>();
		reviews.add(rv1);
		reviews.add(rv2);
	
		when(rv1.getRating()).thenReturn(2);
		when(rv2.getRating()).thenReturn(3);
		
		this.property = new Property(area, country, city, address, maxGuests, description, amenities, maxPicture, type, owner, bookedDays, pictures, reviews);
	}

	@Test
	void testConstructor() {
		assertNotNull(property);
	}

	@Test
	void testSummary() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		property.summary();
		assertEquals("Property Summary:" + System.lineSeparator(), outContent.toString());
	}

	@Test
	void testGetCity() {
		assertEquals(city, property.getCity());
	}
	
	@Test
	void testGetRank() {
		assertEquals(2.5, property.getRank());
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

	@Test
	void testGetReviews() {
		assertEquals(reviews, property.getReviews());
	}
}
