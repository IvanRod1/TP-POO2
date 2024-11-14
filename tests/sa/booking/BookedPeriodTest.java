package sa.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.users.Tenant;


class BookedPeriodTest {

	private BookedPeriod bp;
	private Tenant		 tenant;
	private LocalDate	 dateStart;
	private LocalDate	 dateEnd;
	
	@BeforeEach
	void setUp() throws Exception {
		this.dateStart = LocalDate.now();
		this.dateEnd   = this.dateStart.plusDays(2);
	
		this.tenant	   = mock(Tenant.class);
	
		this.bp = new BookedPeriod(this.tenant, this.dateStart, this.dateEnd);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.bp);
	}
	
	@Test
	void testStart() {
		LocalDate d = this.bp.start();
		assertEquals(d, this.dateStart);
	}
	
	@Test
	void testEnd() {
		LocalDate d = this.bp.end();
		assertEquals(d, this.dateEnd);
	}
	
	@Test
	void testTenant() {
		Tenant t = this.bp.tenant();
		assertEquals(t, this.tenant);
	}
	
	@Test
	void testBelongs() {
		assertTrue(this.bp.belongs(this.dateStart));
		assertFalse(this.bp.belongs(this.dateStart.minusDays(1)));
		assertTrue(this.bp.belongs(this.dateStart.plusDays(1)));
	}

	@Test
	void testEquals() {
		BookedPeriod expected = new BookedPeriod(tenant, dateStart, dateEnd);
		assertTrue(this.bp.equals(expected));
	}
}
