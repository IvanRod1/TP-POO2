package sa.observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjectScreenTest {

	private ObjectScreen test;
	@BeforeEach
	void setUp() throws Exception {
		test = new ObjectScreen();
	}

	@Test
	void newScreenTest() {
		assertNotNull(test);
	}
	@Test
	void screenMethodTest() {
		test.popUp(anyString(), anyString(), anyInt());
	}

}
