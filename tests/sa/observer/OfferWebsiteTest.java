package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;

class OfferWebsiteTest {

	private OfferWebSite website;
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
	
		
		//SUT
		website = new OfferWebSite();
		
		//DOC
		//bookingTest = mock(Booking.class);
		
	}

	@Test
	void newOfferWebSitetest() {
		website = new OfferWebSite();
		assertNotNull(website);
	}
	
	//@Test
	/*void updateOfferWebSiteTest() {
		website.update(bookingTest);
	}
	
	@Test
	void updateOfferWebSiteTest2()
	{
		website.update(bookingTest, bp);
	}*/

}
