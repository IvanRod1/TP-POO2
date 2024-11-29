package sa.booking.reserveStates;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Booking;
import sa.booking.Reserve;
import sa.observer.BookingSubscriber;

class TimerTest {

	private Timer timer;
	
	private List<BookingSubscriber> bsubscribers;
	private BookingSubscriber suscriber1;
	private BookingSubscriber suscriber2;
	
	private Booking	booking1;
	private Booking	booking2;
	
	private Reserve	reserve1;
	private Reserve	reserve2;
	
	private LocalDate date1;
	private LocalDate date2;
	
	@BeforeEach
	void setUp() throws Exception {
		// DOC
		this.suscriber1	= mock(BookingSubscriber.class);
		this.suscriber2	= mock(BookingSubscriber.class);
		this.booking1 	= mock(Booking.class);
		this.booking2 	= mock(Booking.class);
		this.reserve1 	= mock(Reserve.class);
		this.reserve2 	= mock(Reserve.class);
		this.date1		= LocalDate.now();
		this.date2		= this.date1.plusDays(2);
		
		// SUT
		this.timer = new Timer();
	}

	@Test
	void testConstructor() {
		assertNotNull(this.timer);
	}

	@Test
	void testRegister() {
		assertEquals(0, this.timer.getSubscribers().size());
		this.timer.register(booking1, reserve1, date1);
		assertEquals(1, this.timer.getSubscribers().size());
	}

	@Test
	void testUnRegister() {
		assertEquals(0, this.timer.getSubscribers().size());
		this.timer.register(booking1, reserve1, date1);
		assertEquals(1, this.timer.getSubscribers().size());
		this.timer.unregister(booking1, reserve1, date1); 
		assertEquals(0, this.timer.getSubscribers().size()); // No tienen override de equals() y hashCode()
	}

	@Test
	void testNotify() {
		assertEquals(0, this.timer.getSubscribers().size());
		this.timer.register(booking1, reserve1, date1); 
		assertEquals(1, this.timer.getSubscribers().size());
		this.timer.notify(date1); 
		verify(this.booking1, times(1)).update(reserve1, date1);
	}

}
