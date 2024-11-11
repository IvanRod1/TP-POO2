package sa.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;


import java.time.LocalDate;


class SpecialPeriodTest {

	private SpecialPeriod	period;
	
	private double		price;
	private LocalDate	begin;
	private LocalDate	end;
	
	@BeforeEach
	void setUp() throws Exception {
		// DOCs
		this.price	= 1;
		this.begin	= LocalDate.now();
		this.end	= this.begin.plusDays(2);
		
		// SUT
		this.period	= new SpecialPeriod(this.price, this.begin, this.end);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.period);
	}
	
	@Test
	void testStart() {
		assertEquals(this.begin, this.period.start());
	}

	@Test
	void testEnd() {
		assertEquals(this.end, this.period.end());
	}
	
	@Test
	void testBelongs() {
		assertFalse(this.period.belongs(this.begin.minusDays(1)));
		assertTrue(this.period.belongs(this.begin));
		assertTrue(this.period.belongs(this.begin.plusDays(1)));
		assertTrue(this.period.belongs(this.end));
		assertFalse(this.period.belongs(this.end.plusDays(1)));
	}

	@Test
	void testPrice() {
		assertEquals(this.price, this.period.price());
	}
}
