package sa.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.properties.Review;

class OwnerTest {
	
	Review rv1;
	Review rv2;
	List<Review> reviews;
	User tenant;

	@BeforeEach
	void setUp() throws Exception {
		
		
		rv1 = mock(Review.class);
		rv2 = mock(Review.class);

		when(rv1.getRating()).thenReturn(2);
		when(rv2.getRating()).thenReturn(3);
		
		tenant = new Tenant("nacho", 123, "bar@gmail.com");
		
		tenant.getReviews().add(rv1);
		tenant.getReviews().add(rv2);
		
	}

	@Test
	void testRank() {
		assertEquals(2.5, tenant.getRank());
	}

}
