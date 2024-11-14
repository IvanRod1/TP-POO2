package sa.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.users.Tenant;


class BookingScheduleTest {

	private BookingSchedule schedule;
	private HashMap<LocalDate, List<BookedPeriod>> bookedPeriods;
	private List<BookedPeriod>    bps1;
	private List<BookedPeriod>    bps2;
	private BookedPeriod    bp1;
	private BookedPeriod    bp2;
	private Tenant		    tenant1;
	private Tenant		    tenant2;
	private LocalDate	    start1;
	private LocalDate	    end1;
	private LocalDate	    start2;
	private LocalDate	    end2;
	
	@BeforeEach
	void setUp() throws Exception {
	
		this.tenant1   = mock(Tenant.class);
		this.tenant2   = mock(Tenant.class);
		this.bp1	   = mock(BookedPeriod.class);
		this.bp2	   = mock(BookedPeriod.class);
	
		this.start1 = LocalDate.now();
		this.end1   = this.start1.plusDays(2);
		
		this.start2 = this.end1.plusDays(1);
		this.end2   = this.start2.plusDays(2);

		this.bps1 = new ArrayList<BookedPeriod>();
		this.bps2 = new ArrayList<BookedPeriod>();

		this.bookedPeriods = new HashMap<LocalDate, List<BookedPeriod>>();
		this.bookedPeriods.put(start1, bps1);
		this.bookedPeriods.put(start2, bps2);

		when(this.bp1.start()).thenReturn(this.start1);
		when(this.bp2.start()).thenReturn(this.start2);
				
		this.schedule = new BookingSchedule(bookedPeriods);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.schedule);
	}

	@Test
	void testGetBPs() {
		assertEquals(this.bookedPeriods, this.schedule.getBPs());
	}

	@Test
	void testReserves() {
		assertEquals(0, this.schedule.reserves(this.start1).size());
		assertEquals(false, this.schedule.reserves(this.start1).contains(this.bp1));
	}

	@Test
	void testNewBP() {
		BookedPeriod expectedBP = this.schedule.newBP(tenant1, start1, end1);
		assertEquals(this.tenant1, expectedBP.tenant());
		assertEquals(this.start1, expectedBP.start());
		assertEquals(this.end1, expectedBP.end());
	}

	@Test
	void testReserve() {
		assertEquals(2, this.schedule.getBPs().size()); // Comienzan vacías por motivos de testing
		assertEquals(0, this.schedule.reserves(this.start1).size());
		this.schedule.reserve(this.tenant1, this.start1, this.end1);
		assertEquals(1, this.schedule.reserves(this.start1).size());
		assertEquals(2, this.schedule.getBPs().size());
		
		this.schedule.reserve(this.tenant2, this.start1.minusDays(1), this.end1);
		assertEquals(3, this.schedule.getBPs().size());
	}
	
	@Test
	void testGetNext() {
		this.bps1.add(bp1);
		this.bps1.add(bp2);
		assertEquals(this.bp1, this.schedule.getNext(this.start1));
		assertEquals(this.bp2, this.schedule.getNext(this.start1));
	}
	
	@Test
	void testRemove() {
		assertEquals(0, this.schedule.reserves(this.start1).size());
		this.bps1.add(bp1);
		assertEquals(1, this.schedule.reserves(this.start1).size());
		this.schedule.remove(bp1);
		assertFalse(this.schedule.hasReserves(this.start1));
	}

	@Test
	void testHasReserves() {
		this.bps1.add(bp1);
		assertTrue(this.schedule.hasReserves(this.start1));
		this.schedule.remove(bp1);
		assertFalse(this.schedule.hasReserves(this.start1));
	}
	
	@Test
	void testIsEmpty() {
		this.bps1.add(bp1);
		this.bps2.add(bp2);
		assertEquals(false, this.schedule.isEmpty());
		this.schedule.remove(bp1);
		this.schedule.remove(bp2);
		assertEquals(true, this.schedule.isEmpty());
	}
	
}
