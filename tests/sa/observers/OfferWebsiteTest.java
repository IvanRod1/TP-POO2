package sa.observers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Pricer;
import sa.properties.Property;
import sa.properties.PropertyType;

class OfferWebsiteTest {

	private OfferWebsite observertest;
	
	private Booking bookingMock;
	private ObjectPublisher publisher;
	
	private PropertyType type;
	private Property propertyMock;
	private Pricer pricer;
	
	@BeforeEach
	void setUp() throws Exception {
		
		observertest = new OfferWebsite();
		
		
		bookingMock = mock(Booking.class);
		publisher = mock(ObjectPublisher.class);
		type = mock(PropertyType.class);
		propertyMock = mock(Property.class);
		pricer = mock(Pricer.class);
		
		
		observertest.setPublisher(publisher);
		
		
		when(bookingMock.getProperty()).thenReturn(propertyMock);
		when(bookingMock.getPricer()).thenReturn(pricer);
		when(pricer.getBasePrice()).thenReturn(1234.0);
		when(propertyMock.getPropertyType()).thenReturn(type);
		when(type.type()).thenReturn("Departamento");
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
