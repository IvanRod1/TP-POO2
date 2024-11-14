package sa.booking;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sa.booking.PaymentMethod;
import sa.cancellation.CostFree;
import sa.cancellation.ICancellationPolicy;
import sa.cancellation.NoCancellation;
import sa.properties.Property;
import sa.observer.interfaces.INotifyObserver;
import sa.users.Owner;
import sa.users.Tenant;


public class BookingTest {

	private Booking				booking;
	private Booking				bookingReal;
	private ReserveAvailable	stateAvailable;
	private ReserveBooked		stateBooked;
	private ReserveOccupied		stateOccupied;
	
	private List<PaymentMethod> 	paymentMethods 	= new ArrayList<PaymentMethod>();
	private List<INotifyObserver>	obsBasePrice 	= new ArrayList<INotifyObserver>();
	private List<BookedPeriod>	    bps;

	private HashMap<LocalDate, List<BookedPeriod>>		bookedPeriods = new HashMap<LocalDate, List<BookedPeriod>>();
	private HashMap<LocalDate, Set<INotifyObserver>>    obsBP = new HashMap<LocalDate, Set<INotifyObserver>>();
	private HashMap<LocalDate, Set<INotifyObserver>>	obsSP = new HashMap<LocalDate, Set<INotifyObserver>>();
	private HashMap<BookedPeriod, Set<INotifyObserver>> obsCancel = new HashMap<BookedPeriod, Set<INotifyObserver>>();
	private HashMap<BookedPeriod, Set<INotifyObserver>> obsReserve = new HashMap<BookedPeriod, Set<INotifyObserver>>();
	
	private Set<INotifyObserver> setObsBP = new HashSet<INotifyObserver>();
	private Set<INotifyObserver> setObsSP = new HashSet<INotifyObserver>();
	private Set<INotifyObserver> setObsCancel = new HashSet<INotifyObserver>();
	private Set<INotifyObserver> setObsReserve = new HashSet<INotifyObserver>();
	
	// Para DOC Pricer
	private BookingSchedule		schedule;
	private Pricer				pricer;
	private List<SpecialPeriod> periods	= new ArrayList<SpecialPeriod>();
	private SpecialPeriod		period1;
	private SpecialPeriod		period2;
	private SpecialPeriod		period3;
	private double 				pricePerDayWeekday;
	private double 				pricePerDayHighSeason;
	private double 				pricePerDayLongWeekend;

	private INotifyObserver		subscriber1;
	private INotifyObserver		subscriber2;
	private INotifyObserver		subscriber3;
	private INotifyObserver		subscriber4;
	private INotifyObserver		subscriber5;

	private CostFree			policy;
	private Property			property;
	private Owner				owner;
	private Tenant				tenant1;
	private Tenant				tenant2;
	
	private PaymentMethod		paymentMethod1;
	private PaymentMethod		paymentMethod2;

	private LocalDate 			checkIn;
	private LocalDate			checkOut;

	private LocalDate 			today;
	private BookedPeriod		bookedperiod1;
	private BookedPeriod		bookedperiod2;
	private BookedPeriod		bookedperiod3;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateAvailable		= spy(ReserveAvailable.class);
		this.stateBooked		= mock(ReserveBooked.class);
		this.stateOccupied		= mock(ReserveOccupied.class);

		this.schedule			= mock(BookingSchedule.class);
		this.policy				= spy(CostFree.class);
		this.property			= mock(Property.class);
		this.pricer				= mock(Pricer.class);
		this.period1			= mock(SpecialPeriod.class);
		this.period2			= mock(SpecialPeriod.class);
		this.period3			= mock(SpecialPeriod.class);
		this.owner				= mock(Owner.class);
		this.tenant1 	  		= mock(Tenant.class);
		this.tenant2 	  		= mock(Tenant.class);
		this.paymentMethod1 	= mock(PaymentMethod.class);
		this.paymentMethod2 	= mock(PaymentMethod.class);
		this.subscriber1	  	= mock(INotifyObserver.class);
		this.subscriber2	  	= mock(INotifyObserver.class);
		this.subscriber3	  	= mock(INotifyObserver.class);
		this.subscriber4	  	= mock(INotifyObserver.class);
		this.subscriber5	  	= mock(INotifyObserver.class);
		this.bookedperiod1		= mock(BookedPeriod.class);
		this.bookedperiod2		= mock(BookedPeriod.class);
		this.bookedperiod3		= mock(BookedPeriod.class);
		
		this.periods.add(period1);
		this.periods.add(period2);
		this.periods.add(period3);
		this.paymentMethods.add(paymentMethod1);
		this.paymentMethods.add(paymentMethod2);

		this.setObsBP.add(subscriber1);
		this.setObsSP.add(subscriber2);
		this.setObsCancel.add(subscriber3);
		this.setObsReserve.add(subscriber4);
		this.obsBasePrice.add(subscriber5);
		
		this.today 				= LocalDate.now();
		this.checkIn			= this.today;
		this.checkOut			= this.today.plusDays(4);
		this.pricePerDayWeekday		= 5;
		this.pricePerDayHighSeason	= 6;
		this.pricePerDayLongWeekend	= 7;
		this.bps					= new ArrayList<BookedPeriod>();
		this.bookedPeriods.put(today, bps);

		this.obsSP.put(this.checkIn.plusDays(2), this.setObsSP);
		this.obsBP.put(this.checkIn, this.setObsBP);
		this.obsCancel.put(this.bookedperiod1, this.setObsCancel);
		this.obsReserve.put(this.bookedperiod1, this.setObsReserve);

		
		when(this.stateAvailable.next()).thenReturn(stateBooked);
		when(this.stateBooked.next()).thenReturn(stateOccupied);
		when(this.stateOccupied.next()).thenReturn(stateAvailable);

		when(this.period1.price()).thenReturn(pricePerDayHighSeason);
		when(this.period1.start()).thenReturn(this.checkIn.plusDays(2));
		when(this.period1.end()).thenReturn(this.checkIn.plusDays(3));
		when(this.period1.belongs(this.checkIn.plusDays(2))).thenReturn(true);
		when(this.period1.belongs(this.checkIn.plusDays(3))).thenReturn(true);

		when(this.pricer.price(this.checkIn)).thenReturn(pricePerDayWeekday);
		when(this.pricer.price(this.checkIn.plusDays(1))).thenReturn(pricePerDayWeekday);
		when(this.pricer.price(this.checkIn.plusDays(2))).thenReturn(pricePerDayHighSeason);
		when(this.pricer.price(this.checkIn.plusDays(3))).thenReturn(pricePerDayHighSeason);
		when(this.pricer.priceBetween(this.checkIn, this.checkOut)).thenReturn(pricePerDayWeekday+pricePerDayHighSeason+pricePerDayLongWeekend);
		when(this.pricer.getSPeriods()).thenReturn(this.periods);
		when(this.property.getOwner()).thenReturn(this.owner);

		when(this.schedule.getBPs()).thenReturn(bookedPeriods);
		when(this.schedule.isEmpty()).thenReturn(bookedPeriods.isEmpty());
		when(this.schedule.reserves(this.today)).thenReturn(bookedPeriods.get(this.today));

		// Alquila 1 día
		when(this.bookedperiod1.start()).thenReturn(this.today);
		when(this.bookedperiod1.end()).thenReturn(this.today);
		when(this.bookedperiod1.belongs(this.today)).thenReturn(true);
		when(this.bookedperiod1.belongs(this.checkIn)).thenReturn(true);
		when(this.bookedperiod1.belongs(this.checkOut)).thenReturn(false);

		// Alquila 2 día
		when(this.bookedperiod2.start()).thenReturn(this.today);
		when(this.bookedperiod2.end()).thenReturn(this.today.plusDays(1));
		when(this.bookedperiod2.belongs(this.today)).thenReturn(true);
		when(this.bookedperiod2.belongs(this.checkIn)).thenReturn(true);
		when(this.bookedperiod2.belongs(this.checkOut)).thenReturn(false);
		
		// Alquila los 5 día
		when(this.bookedperiod3.start()).thenReturn(this.today);
		when(this.bookedperiod3.end()).thenReturn(this.checkOut);
		when(this.bookedperiod3.belongs(this.today)).thenReturn(true);
		when(this.bookedperiod3.belongs(this.checkIn)).thenReturn(true);
		when(this.bookedperiod3.belongs(this.checkOut)).thenReturn(true);
	

		// SUT (System Under Test): objeto a testear
		this.booking = new Booking(   stateAvailable
									, policy
									, pricer
									, property
									, checkIn
									, checkOut
									, paymentMethods
									, pricePerDayWeekday
									, periods
									, obsSP
									, obsBP
									, obsCancel
									, obsReserve
									, schedule
									, obsBasePrice );

		this.bookingReal = new Booking(   property
										, checkIn
										, checkOut
										, paymentMethods
										, pricePerDayWeekday
										, periods );
	}

	@Test
	public void testConstructor() {
		assertNotNull(this.booking);
	}

	@Test
	public void testConstructorReal() {
		assertNotNull(this.bookingReal);
	}


	@Test
	public void testGetPaymentMethods() {
		assertEquals(this.paymentMethods, this.booking.getPaymentMethods());
	}

	@Test
	public void testGetProperty() {
		assertEquals(this.property, this.booking.getProperty());
	}
	
	@Test
	public void testGetTenant() {
		assertNull(this.booking.getTenant());
	}

	@Test
	public void testSetState() {
		assertNotNull(this.booking.getState());
		this.booking.setState(this.stateBooked);
		assertEquals(this.stateBooked, this.booking.getState());
	}
	
	@Test
	public void testFromAvailableToBooked() {
		assertEquals(this.stateAvailable, this.booking.getState());
		assertEquals(this.stateBooked, this.stateAvailable.next());
		this.booking.setState(this.booking.getState().next());
		assertEquals(this.stateBooked, this.booking.getState());
	}

	@Test
	public void testSetCancellationPolicy() {
		assertNotNull(this.booking.getPolicy());
		verifyNoInteractions(this.policy);
		NoCancellation noCPolicy = spy(NoCancellation.class);
		this.booking.setCancellationPolicy(noCPolicy);
		assertEquals(noCPolicy, this.booking.getPolicy());
		verifyNoInteractions(noCPolicy);
	}

	@Test
	public void testApplyPolicy() {
		assertNotNull(this.booking.getPolicy());
		verifyNoInteractions(this.policy);
		this.booking.applyPolicy(this.bookedperiod1);
		verify(this.policy).activate(checkIn, booking, bookedperiod1);
	}

	@Test
	public void testSetBasePrice() {
		verify(this.pricer, times(0)).price(this.checkIn);
		assertEquals(this.pricePerDayWeekday, this.booking.price(this.checkIn));
		verify(this.pricer, times(1)).price(this.checkIn);
		verify(this.pricer, times(0)).getBasePrice();
		verify(this.pricer, times(0)).setBasePrice(pricePerDayWeekday*0.5);
		this.booking.setBasePrice(pricePerDayWeekday*0.5);
		when(this.pricer.getBasePrice()).thenReturn(pricePerDayWeekday);
		verify(this.pricer, times(1)).setBasePrice(pricePerDayWeekday*0.5);
	}
	
	@Test
	public void testPrice() {
		assertEquals(this.pricePerDayWeekday, this.booking.price(this.checkIn));
	}
	
	@Test
	public void testPriceBetween() {
		double expectedPrice = this.pricePerDayWeekday + this.pricePerDayHighSeason + this.pricePerDayLongWeekend; 
		assertEquals(expectedPrice, this.booking.priceBetween(this.checkIn, this.checkOut));
	}
	
	@Test
	public void testReserve() {
		this.booking.reserve(this.tenant1, this.today, this.today.plusDays(1));
		verify(this.schedule, times(1)).reserve(this.tenant1, this.today, this.today.plusDays(1));
	}

	@Test
	public void testApproveReserve() {
		this.booking.approveReserve(bookedperiod1);
		verify(this.stateAvailable, times(1)).approveReserve(this.booking, this.bookedperiod1);
	}
	
	@Test
	public void testCancelReserve() {
		this.booking.cancelReserve(this.bookedperiod1);
		verify(this.stateAvailable, times(1)).cancelReserve(this.booking, this.bookedperiod1);
	}

	@Test
	public void testTriggerNextRequest() {
		when(this.schedule.hasReserves(this.today)).thenReturn(true);
		when(this.schedule.getNext(this.today)).thenReturn(this.bookedperiod1);
		this.booking.triggerNextRequest();
		verify(this.stateAvailable, times(1)).requestReserve(this.booking, this.bookedperiod1);
		
		when(this.schedule.isEmpty()).thenReturn(true);
		this.booking.triggerNextRequest();
		verify(this.stateAvailable, times(1)).requestReserve(this.booking, this.bookedperiod1);
	}

	@Test
	public void testNotifySubscribersLowerPrice() {
		verify(this.subscriber5, times(0)).update(this.booking);
		when(this.pricer.getBasePrice()).thenReturn(pricePerDayWeekday);
		this.booking.setBasePrice(pricePerDayWeekday*0.5);
		verify(this.subscriber5, times(1)).update(this.booking);
	}
	
	@Test
	public void testRegisterPriceObserver() {
		assertEquals(1, this.obsBP.size());
		assertEquals(1, this.obsSP.size());
		assertEquals(1, this.setObsBP.size());
		assertEquals(1, this.setObsSP.size());
		INotifyObserver expectedObs = mock(INotifyObserver.class);
		this.booking.registerPriceObserver(expectedObs, this.checkIn);
		this.booking.registerPriceObserver(expectedObs, this.checkIn.plusDays(1), this.checkIn.plusDays(3));
		assertEquals(2, this.obsBP.size());
		assertEquals(2, this.setObsBP.size());
		assertEquals(2, this.obsSP.size());
		assertEquals(2, this.setObsSP.size());
	}

	@Test
	public void testUnregisterPriceObserver() {
		assertEquals(1, this.setObsBP.size());
		this.booking.unRegisterPriceObserver(this.subscriber1, this.checkIn);
		assertEquals(1, this.obsBP.size());
		assertEquals(0, this.setObsBP.size());
		assertEquals(1, this.setObsSP.size());
		this.booking.unRegisterPriceObserver(this.subscriber2, this.checkIn.plusDays(2));
		assertEquals(1, this.obsSP.size());
		assertEquals(0, this.setObsSP.size());
	}

	@Test
	public void testNotifySubscribersPrice() {
		verify(this.subscriber1, times(0)).update(this.booking, this.checkIn);
		verify(this.subscriber2, times(0)).update(this.booking, this.checkIn);
		this.booking.notifySubscribersPrice(this.booking, this.checkIn);
		this.booking.notifySubscribersPrice(this.booking, this.checkIn.plusDays(2));
		verify(this.subscriber1, times(1)).update(this.booking, this.checkIn);
		verify(this.subscriber2, times(1)).update(this.booking, this.checkIn.plusDays(2));
	}

	@Test
	public void testRegisterCancelObserver() {
		assertEquals(1, this.obsCancel.size());
		assertEquals(1, this.setObsCancel.size());
		INotifyObserver expectedObs = mock(INotifyObserver.class);
		this.booking.registerCancelObserver(expectedObs, this.bookedperiod1);
		this.booking.registerCancelObserver(expectedObs, this.bookedperiod2);
		assertEquals(2, this.obsCancel.size());
		assertEquals(2, this.setObsCancel.size());
	}

	@Test
	public void testUnregisterCancelObserver() {
		assertEquals(1, this.obsCancel.size());
		assertEquals(1, this.setObsCancel.size());
		this.booking.unRegisterCancelObserver(this.subscriber3, this.bookedperiod1);
		assertEquals(1, this.obsCancel.size());
		assertEquals(0, this.setObsCancel.size());
	}

	@Test
	public void testNotifySubscribersCancelled() {
		verify(this.subscriber3, times(0)).update(this.booking, this.bookedperiod1);
		this.booking.notifySubscribersCancelled(this.booking, this.bookedperiod1);
		verify(this.subscriber3, times(1)).update(this.booking, this.bookedperiod1);
	}

	@Test
	public void testRegisterReserveObserver() {
		assertEquals(1, this.obsReserve.size());
		assertEquals(1, this.setObsReserve.size());
		INotifyObserver expectedObs = mock(INotifyObserver.class);
		this.booking.registerReserveObserver(expectedObs, this.bookedperiod1);
		this.booking.registerReserveObserver(expectedObs, this.bookedperiod2);
		assertEquals(2, this.obsReserve.size());
		assertEquals(2, this.setObsReserve.size());
	}

	@Test
	public void testUnregisterReserveObserver() {
		assertEquals(1, this.obsReserve.size());
		assertEquals(1, this.setObsReserve.size());
		this.booking.unRegisterReserveObserver(this.subscriber4, this.bookedperiod1);
		assertEquals(1, this.obsReserve.size());
		assertEquals(0, this.setObsReserve.size());
	}

	@Test
	public void testNotifySubscribersReserve() {
		verify(this.subscriber4, times(0)).update(this.booking, this.bookedperiod1);
		this.booking.notifySubscribersReserve(this.booking, this.bookedperiod1);
		verify(this.subscriber4, times(1)).update(this.booking, this.bookedperiod1);
	}

}
