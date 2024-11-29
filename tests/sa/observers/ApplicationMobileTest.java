package sa.observers;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.properties.Property;
import sa.properties.PropertyType;

class ApplicationMobileTest {

	private ApplicationMobile observertest;
	
	private Booking bookingMock;
	private Property propertyMock;
	private PropertyType type;
	private ObjectScreen screenMock;
	
	@BeforeEach
	void setUp() throws Exception {
		observertest = new ApplicationMobile();
		
		bookingMock = mock(Booking.class);
		
		propertyMock = mock(Property.class);
		type = mock(PropertyType.class);
		screenMock = mock(ObjectScreen.class);
		
		observertest.setScreen(screenMock);
		
		when(bookingMock.getProperty()).thenReturn(propertyMock);
		when(propertyMock.getPropertyType()).thenReturn(type);
		when(type.type()).thenReturn("Casa Quinta");
	}

	@Test
	void newObserverTest() {
		assertNotNull(observertest);
	}
	
	@Test
	void observerGettingNotified() {
		
		observertest.update(bookingMock);
		verify(propertyMock);
		assertNotNull(observertest.getScreen());
	}

}
