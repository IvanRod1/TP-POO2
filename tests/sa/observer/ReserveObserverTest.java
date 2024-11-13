package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.properties.Property;

class ReserveObserverTest {

	private ReserveObserver observer;
	private Booking bookingMock;
	@BeforeEach
	void setUp() throws Exception {
		observer = new ReserveObserver();
		
		bookingMock = mock(Booking.class);
	}

	@Test
	void newReserveObserverTest() {
		observer = new ReserveObserver();
		assertNotNull(observer);
	}
	
	@Test
	void updateObserver()
	{
		Property auxProperty = mock(Property.class);
		
		when(bookingMock.getProperty()).thenReturn(auxProperty);
		observer.update(bookingMock);
		
		
		verify(auxProperty).summary();
	}

}
