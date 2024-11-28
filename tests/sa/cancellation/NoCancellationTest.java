package sa.cancellation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.booking.Reserve;

class NoCancellationTest {

	private NoCancellation cancellationtest;
	private Reserve reserveMock;
	
	
	@BeforeEach
	void setUp() throws Exception {
		cancellationtest = new NoCancellation();
	}

	@Test
	void newCancellationTest() {
		assertNotNull(cancellationtest);
	}
	
	@Test
	void activateCancellationTest() {
		cancellationtest.activate(reserveMock);
	}

}
