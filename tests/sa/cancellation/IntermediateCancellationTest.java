package sa.cancellation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sa.booking.Booking;

class IntermediateCancellationTest {

	private IntermediateCancellation intermediateCancellation;
	private Booking bookingTest;
	private LocalDate checkInDate;

	@BeforeEach
	void setUp() throws Exception {
		//DOCS
		bookingTest = mock(Booking.class);
		checkInDate = LocalDate.of(2024, 11, 24);
		when(bookingTest.getCheckIn()).thenReturn(checkInDate);
		when(bookingTest.price(bookingTest.getCheckIn())).thenReturn((double) 123000);
		
		//SUT
		intermediateCancellation = new IntermediateCancellation();
	}

	@Test
	void newIntermediateCancellationtest() {
		intermediateCancellation = new IntermediateCancellation();
		assertNotNull(intermediateCancellation);
	}
	@Test
	void activateIntermediateCancellation22DaysBeforeTest() {
		
		IntermediateCancellation spy = Mockito.spy(intermediateCancellation);
		spy.activate(checkInDate.minusDays(22),bookingTest);
		verify(spy, atLeastOnce()).activate(checkInDate.minusDays(22), bookingTest);

	}
	
	@Test
	void activateIntermediateCancellation15DaysBeforeTest() {
		
		IntermediateCancellation spy = Mockito.spy(intermediateCancellation);
		spy.activate(checkInDate.minusDays(15),bookingTest);
		verify(spy, atLeastOnce()).activate(checkInDate.minusDays(15), bookingTest);
	}
	@Test
	void activateIntermediateCancellation5DaysBeforeTest() {
		IntermediateCancellation spy = Mockito.spy(intermediateCancellation);
		spy.activate(checkInDate.minusDays(5),bookingTest);
		verify(spy, atLeastOnce()).activate(checkInDate.minusDays(5), bookingTest);
	}
	

}
