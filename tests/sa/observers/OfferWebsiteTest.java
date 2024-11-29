package sa.observers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;

class OfferWebsiteTest {

	private OfferWebsite observertest;
	
	private Booking bookingMock;
	private ObjectPublisher publisher;
	
	@BeforeEach
	void setUp() throws Exception {
		
		observertest = new OfferWebsite();
		
		
		bookingMock = mock(Booking.class);
		publisher = mock(ObjectPublisher.class);
		
		observertest.setPublisher(publisher);
	}

	@Test
	void newObserverTest() {
		assertNotNull(observertest);
	}
	
	@Test
	void observerGettingNotified() {
		observertest.update(bookingMock);
	}

}
