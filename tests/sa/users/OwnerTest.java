package sa.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import sa.booking.Period;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.properties.Review;

class OwnerTest {
	
	Review rv1;
	Review rv2;
	List<Review> reviews;
	User owner;
	Reserve requestedReserve;
	Booking booking;
	Tenant tenant;
	Period period;

	@BeforeEach
	void setUp() throws Exception {
		
		
		rv1 = mock(Review.class);
		rv2 = mock(Review.class);

		when(rv1.getRating()).thenReturn(2);
		when(rv2.getRating()).thenReturn(3);
		
		owner = new Tenant("nacho", 123, "bar@gmail.com");
		
		owner.getReviews().add(rv1);
		owner.getReviews().add(rv2);
		
		booking = mock(Booking.class);
		tenant = mock(Tenant.class);
		period = mock(Period.class);
		
		requestedReserve = spy(new Reserve(booking, tenant, period));
	}

	@Test
	void testRank() {
		assertEquals(2.5, owner.getRank());
	}

	@Test
	void summaryTest() {
		owner.summary();
	}
	
	@Test
	void reserveRequestedOnTest() {
		
		owner.reserveRequestedOn(requestedReserve);
		
		verify(requestedReserve, times(1)).getTenant().summary();
	}
	
	@Test
	void getRequestedReserveTest() {
		assertEquals(null, owner.getRequestedReserve());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
