package sa.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.booking.reserveStates.ReserveBooked;
import sa.booking.reserveStates.ReserveOccupied;
import sa.cancellation.CostFree;
import sa.cancellation.NoCancellation;
import sa.properties.Property;
import sa.observer.interfaces.INotifyObserver;
import sa.users.Owner;
import sa.users.Tenant;


public class BookingTest {

	private Booking				booking;
	private Booking				bookingReal;
	private ReserveBooked		stateBooked;
	private ReserveOccupied		stateOccupied;
	private Timer				timer;

	private List<PaymentMethod> 		paymentMethods 	= new ArrayList<PaymentMethod>();
	private Period	    				period;
	private List<Reserve> 				reserves = new ArrayList<Reserve>();
	private List<Reserve>				waitings = new ArrayList<Reserve>();
	private List<INotifyObserver> 		obsPrice = new ArrayList<INotifyObserver>();
	private List<INotifyObserver> 		obsCancel = new ArrayList<INotifyObserver>();
	private List<INotifyObserver> 		obsReserve = new ArrayList<INotifyObserver>();

	
	// Para DOC Pricer
	private Pricer				pricer;
	private List<SpecialPeriod> specialPeriods	= new ArrayList<SpecialPeriod>();
	private SpecialPeriod		specialPeriod1;
	private SpecialPeriod		specialPeriod2;
	private SpecialPeriod		specialPeriod3;
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
	private Tenant				tenant3;
	
	private PaymentMethod		paymentMethod1;
	private PaymentMethod		paymentMethod2;

	private LocalDate 			begin;
	private LocalDate			end;

	private LocalDate 			today;
	private Reserve				reserve1;
	private Reserve				reserve2;
	private Reserve				reserve3;
	private Period				bookedperiod1;
	private Period				bookedperiod2;
	private Period				bookedperiod3;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateBooked		= mock(ReserveBooked.class);
		this.stateOccupied		= mock(ReserveOccupied.class);

		this.policy				= spy(CostFree.class);
		this.property			= mock(Property.class);
		this.pricer				= mock(Pricer.class);
		this.specialPeriod1		= mock(SpecialPeriod.class);
		this.specialPeriod2		= mock(SpecialPeriod.class);
		this.specialPeriod3		= mock(SpecialPeriod.class);
		this.owner				= spy(Owner.class);
		this.tenant1 	  		= mock(Tenant.class);
		this.tenant2 	  		= mock(Tenant.class);
		this.tenant3 	  		= mock(Tenant.class);
		this.paymentMethod1 	= mock(PaymentMethod.class);
		this.paymentMethod2 	= mock(PaymentMethod.class);
		this.subscriber1	  	= mock(INotifyObserver.class);
		this.subscriber2	  	= mock(INotifyObserver.class);
		this.subscriber3	  	= mock(INotifyObserver.class);
		this.subscriber4	  	= mock(INotifyObserver.class);
		this.subscriber5	  	= mock(INotifyObserver.class);
		this.reserve1			= mock(Reserve.class);
		this.reserve2			= mock(Reserve.class);
		this.reserve3			= mock(Reserve.class);
		this.bookedperiod1		= mock(Period.class);
		this.bookedperiod2		= mock(Period.class);
		this.bookedperiod3		= mock(Period.class);
		this.timer				= spy(Timer.class);
		
		this.specialPeriods.add(specialPeriod1);
		this.specialPeriods.add(specialPeriod2);
		this.specialPeriods.add(specialPeriod3);
		this.paymentMethods.add(paymentMethod1);
		this.paymentMethods.add(paymentMethod2);

		this.obsPrice.add(subscriber1);
		this.obsCancel.add(subscriber2);
		this.obsReserve.add(subscriber3);
		
		this.today 				= LocalDate.now();
		this.begin				= this.today;
		this.end				= this.today.plusDays(4);
		this.pricePerDayWeekday		= 5;
		this.pricePerDayHighSeason	= 6;
		this.pricePerDayLongWeekend	= 7;

		when(this.reserve1.getCheckIn()).thenReturn(this.today);
		when(this.reserve1.getCheckOut()).thenReturn(this.today);
		when(this.reserve1.getTenant()).thenReturn(this.tenant1);
		
		when(this.specialPeriod1.price()).thenReturn(pricePerDayHighSeason);
		when(this.specialPeriod1.start()).thenReturn(this.begin.plusDays(2));
		when(this.specialPeriod1.end()).thenReturn(this.begin.plusDays(3));
		when(this.specialPeriod1.belongs(this.begin.plusDays(2))).thenReturn(true);
		when(this.specialPeriod1.belongs(this.begin.plusDays(3))).thenReturn(true);

		when(this.pricer.price(this.begin)).thenReturn(pricePerDayWeekday);
		when(this.pricer.price(this.begin.plusDays(1))).thenReturn(pricePerDayWeekday);
		when(this.pricer.price(this.begin.plusDays(2))).thenReturn(pricePerDayHighSeason);
		when(this.pricer.price(this.begin.plusDays(3))).thenReturn(pricePerDayHighSeason);
		when(this.pricer.priceBetween(this.begin, this.end)).thenReturn(pricePerDayWeekday+pricePerDayHighSeason+pricePerDayLongWeekend);
		when(this.pricer.getSPeriods()).thenReturn(this.specialPeriods);

		when(this.property.getOwner()).thenReturn(this.owner);
		

		// Alquila 1 día
		when(this.bookedperiod1.start()).thenReturn(this.today);
		when(this.bookedperiod1.end()).thenReturn(this.today);
		when(this.bookedperiod1.belongs(this.today)).thenReturn(true);
		when(this.bookedperiod1.belongs(this.begin)).thenReturn(true);
		when(this.bookedperiod1.belongs(this.end)).thenReturn(false);
		when(this.reserve1.getPeriod()).thenReturn(this.bookedperiod1);
//		when(this.reserve1.getCheckIn()).thenReturn(this.bookedperiod1.start());
//		when(this.reserve1.getCheckOut()).thenReturn(this.bookedperiod1.end());


		// Alquila 2 día
		when(this.bookedperiod2.start()).thenReturn(this.today);
		when(this.bookedperiod2.end()).thenReturn(this.today.plusDays(1));
		when(this.bookedperiod2.belongs(this.today)).thenReturn(true);
		when(this.bookedperiod2.belongs(this.begin)).thenReturn(true);
		when(this.bookedperiod2.belongs(this.end)).thenReturn(false);
		when(this.reserve2.getPeriod()).thenReturn(this.bookedperiod2);
//		when(this.reserve2.getCheckIn()).thenReturn(this.bookedperiod2.start());
//		when(this.reserve2.getCheckOut()).thenReturn(this.bookedperiod2.end());
		
		// Alquila los 5 día
		when(this.bookedperiod3.start()).thenReturn(this.today);
		when(this.bookedperiod3.end()).thenReturn(this.end);
		when(this.bookedperiod3.belongs(this.today)).thenReturn(true);
		when(this.bookedperiod3.belongs(this.begin)).thenReturn(true);
		when(this.bookedperiod3.belongs(this.end)).thenReturn(true);
		when(this.reserve3.getPeriod()).thenReturn(this.bookedperiod3);
//		when(this.reserve3.getCheckIn()).thenReturn(this.bookedperiod3.start());
//		when(this.reserve3.getCheckOut()).thenReturn(this.bookedperiod3.end());

		// SUT (System Under Test): objeto a testear
		this.booking = new Booking(   policy
									, pricer
									, property
									, paymentMethods
									, pricePerDayWeekday
									, specialPeriods
									, period
									, reserves
									, waitings
									, timer
									, obsCancel
									, obsReserve
									, obsPrice );

		this.bookingReal = new Booking(   property
										, begin
										, end
										, paymentMethods
										, pricePerDayWeekday
										, specialPeriods );
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
	public void testGetPeriod() {
		assertEquals(this.period, this.booking.getPeriod());
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
	public void testGetPolicy() {
		assertEquals(this.policy, this.booking.getPolicy());
	}


	@Test
	public void testSetCancellationPolicy() {
		assertNotNull(this.booking.getPolicy());
		verifyNoInteractions(this.policy);
		NoCancellation noCPolicy = mock(NoCancellation.class);
		this.booking.setCancellationPolicy(noCPolicy);
		assertEquals(noCPolicy, this.booking.getPolicy());
		verifyNoInteractions(noCPolicy);
	}

	@Test
	public void testApplyPolicy() {
		assertNotNull(this.booking.getPolicy());
		verifyNoInteractions(this.policy);
		this.booking.applyPolicy(this.reserve1);
		verify(this.policy).activate(this.reserve1);
	}

	@Test
	public void testSetBasePrice() {
		verify(this.pricer, times(0)).price(this.begin);
		assertEquals(this.pricePerDayWeekday, this.booking.price(this.begin));
		verify(this.pricer, times(1)).price(this.begin);
		verify(this.pricer, times(0)).getBasePrice();
		verify(this.pricer, times(0)).setBasePrice(pricePerDayWeekday*0.5);
		this.booking.setBasePrice(pricePerDayWeekday*0.5);
		when(this.pricer.getBasePrice()).thenReturn(pricePerDayWeekday);
		verify(this.pricer, times(1)).setBasePrice(pricePerDayWeekday*0.5);
	}
	
	@Test
	public void testPrice() {
		assertEquals(this.pricePerDayWeekday, this.booking.price(this.begin));
	}
	
	@Test
	public void testPriceBetween() {
		double expectedPrice = this.pricePerDayWeekday + this.pricePerDayHighSeason + this.pricePerDayLongWeekend; 
		assertEquals(expectedPrice, this.booking.priceBetween(this.begin, this.end));
	}
	
	@Test
	public void testNewReserve() {
		assertEquals(0, this.booking.getReserves().size());
		verifyNoInteractions(this.owner);
		this.booking.newReserve(this.tenant1, this.today, this.today.plusDays(1));
		Reserve r = this.owner.getRequestedReserve();
		r.approve();
		assertEquals(1, this.booking.getReserves().size());
	}

	@Test
	public void testNewConditionalReserve() {
		assertEquals(0, this.booking.getConditionalReserves().size());
		this.booking.newConditionalReserve(this.tenant1, this.today, this.today.plusDays(1));
		assertEquals(1, this.booking.getConditionalReserves().size());
	}

	@Test
	public void testTriggerNextRequest() {
		assertEquals(0, this.booking.getConditionalReserves().size());
		this.waitings.add(this.reserve1);
		assertEquals(1, this.booking.getConditionalReserves().size());
		this.booking.triggerNextRequest(this.reserve1.getCheckIn());
		verify(this.owner, times(1)).reserveRequestedOn(this.reserve1);
		assertEquals(0, this.booking.getConditionalReserves().size());
	}

	// Subscribers
	@Test
	public void testNotifySubscribersLowerPrice() {
		verify(this.subscriber5, times(0)).updateLowerPrice(this.booking);
		when(this.pricer.getBasePrice()).thenReturn(pricePerDayWeekday);
		this.obsPrice.add(this.subscriber5);
		this.booking.setBasePrice(pricePerDayWeekday*0.5);
		verify(this.subscriber5, times(1)).updateLowerPrice(this.booking);
	}

	@Test
	public void testRegisterPriceObserver() {
		assertEquals(1, this.obsPrice.size());
		this.booking.registerPriceObserver(this.subscriber4);
		assertEquals(2, this.obsPrice.size());
	}

	@Test
	public void testUnregisterPriceObserver() {
		assertEquals(1, this.obsPrice.size());
		this.booking.unRegisterPriceObserver(subscriber1);
		assertEquals(0, this.obsPrice.size());
	}

	@Test
	public void testNotifySubscribersPrice() {
		verify(this.subscriber1, times(0)).updateLowerPrice(booking);
		this.booking.notifySubscribersPrice();
		verify(this.subscriber1, times(1)).updateLowerPrice(booking);
	}

	@Test
	public void testRegisterCancelObserver() {
		assertEquals(1, this.obsCancel.size());
		this.booking.registerCancelObserver(subscriber4);
		assertEquals(2, this.obsCancel.size());
	}

	@Test
	public void testUnregisterCancelObserver() {
		assertEquals(1, this.obsCancel.size());
		this.booking.unRegisterCancelObserver(subscriber2);
		assertEquals(0, this.obsCancel.size());
	}

	@Test
	public void testNotifySubscribersCancelled() {
		verify(this.subscriber2, times(0)).updateCancellation(reserve1);
		this.booking.notifySubscribersCancelled(reserve1);
		verify(this.subscriber2, times(1)).updateCancellation(reserve1);
	}

	@Test
	public void testRegisterReserveObserver() {
		assertEquals(1, this.obsReserve.size());
		this.booking.registerReserveObserver(subscriber4);
		assertEquals(2, this.obsReserve.size());
	}

	@Test
	public void testUnregisterReserveObserver() {
		assertEquals(1, this.obsReserve.size());
		this.booking.unRegisterReserveObserver(subscriber3);
		assertEquals(0, this.obsReserve.size());
	}

	@Test
	public void testNotifySubscribersReserve() {
		verify(this.subscriber3, times(0)).updateNewReserve(reserve1);
		this.booking.notifySubscribersReserve(reserve1);
		verify(this.subscriber3, times(1)).updateNewReserve(reserve1);
	}
	
	@Test
	public void testUpdate() {
		assertEquals(0, this.reserves.size());
		this.reserves.add(this.reserve1);
		assertEquals(1, this.reserves.size());
		verify(this.reserve1, times(0)).getCheckOut();
		this.booking.update(this.reserve1, this.today);
		verify(this.reserve1, times(1)).getCheckOut();

		assertEquals(0, this.reserves.size());
		this.reserves.add(this.reserve1);
		assertEquals(1, this.reserves.size());
		verify(this.reserve1, times(1)).getCheckOut();
		verify(this.reserve1, times(0)).next();
		this.booking.update(this.reserve1, this.today.minusDays(1));
		verify(this.reserve1, times(3)).getCheckOut();
		verify(this.reserve1, times(1)).next();
	}

	@Test
	public void testSetSPPrice() {
		verify(this.subscriber1, times(0)).updateLowerPrice(this.booking);
		double newPrice = pricePerDayHighSeason*0.5;
		verify(this.specialPeriod1, times(0)).price();
		verify(this.specialPeriod1, times(0)).setPrice(newPrice);
		this.booking.setSPPrice(newPrice, this.specialPeriod1);
		verify(this.specialPeriod1, times(1)).price();
		verify(this.specialPeriod1, times(1)).setPrice(newPrice);
		verify(this.subscriber1, times(1)).updateLowerPrice(this.booking);
	}
	
}
