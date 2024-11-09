package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;

class applicationMobileTest {

	private ApplicationMobile appMobileObserver;
	private ObjectScreen screenTest;
	private Booking bookingTest;
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		appMobileObserver = new ApplicationMobile();
		
		//DOC
		screenTest = mock(ObjectScreen.class);
		//when(appMobileObserver.getScreen()).thenReturn(screenTest);
		
		bookingTest = mock(Booking.class);
		when(bookingTest.getState()).thenReturn("Available");
		
	}

	@Test
	void newAppMobileObservertest() {
		appMobileObserver = new ApplicationMobile();
	}
	@Test
	void updateObserverAppMobileTest() {
		appMobileObserver.setScreen(screenTest);
		appMobileObserver.update(bookingTest); 
	}
	
	

}
