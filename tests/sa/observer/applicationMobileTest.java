package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.IReserveState;

class applicationMobileTest {

	private ApplicationMobile appMobileObserver;
	private ObjectScreen screenTest;
	private Booking bookingTest;
	private IReserveState estado;
	@BeforeEach
	void setUp() throws Exception {
		//SUT
		appMobileObserver = new ApplicationMobile();
		
		//DOC
		screenTest = mock(ObjectScreen.class);
		//when(appMobileObserver.getScreen()).thenReturn(screenTest);
		
		bookingTest = mock(Booking.class);
		estado = mock(IReserveState.class);
		when(bookingTest.getState()).thenReturn(estado);
		
	}

	@Test
	void newAppMobileObservertest() {
		appMobileObserver = new ApplicationMobile();
		assertNotNull(appMobileObserver);
	}
	@Test
	void updateObserverAppMobileTest() {
		appMobileObserver.setScreen(screenTest);
		appMobileObserver.update(bookingTest); 
		verify(screenTest);
	}
	
	 
}
