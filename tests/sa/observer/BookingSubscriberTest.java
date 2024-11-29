package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Reserve;

class BookingSubscriberTest {

	private BookingSubscriber 	bsubscriber;
	
	private Booking 	booking;
	private Reserve 	reserve;
	private LocalDate	date;
	
	@BeforeEach
	void setUp() throws Exception {
		this.booking		= mock(Booking.class);
		this.reserve		= mock(Reserve.class);
		this.date			= LocalDate.now();
		
		this.bsubscriber = new BookingSubscriber(booking, reserve, date);
	}


	@Test
	void testConstructor() {
		assertNotNull(this.bsubscriber);
	}

	@Test
	void testGetBooking() {
		assertEquals(this.booking, this.bsubscriber.getBooking());
	}

	@Test
	void testGetReserve() {
		assertEquals(this.reserve, this.bsubscriber.getReserve());
	}

	@Test
	void testGetDate() {
		assertEquals(this.date, this.bsubscriber.getDate());
	}
}
