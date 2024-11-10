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
import java.util.List;

import sa.payments.PaymentMethod;
import sa.policies.CostFree;
import sa.policies.ICancellationPolicy;
import sa.policies.NoCancellation;
import sa.properties.Property;
import sa.subscriptions.INotifyObserver;
import sa.users.Owner;
//import sa.users.Owner;
import sa.users.Tenant;


public class BookingTest {

	private Booking				booking;
	private Booking				bookingReal;
	private ReserveAvailable	stateAvailable;
	private ReserveApproved		stateApproved;
	private ReserveCompleted	stateCompleted;
	
	private List<Tenant> 			waitingTenants 	= new ArrayList<Tenant>();
	private List<PaymentMethod> 	paymentMethods 	= new ArrayList<PaymentMethod>();
	private List<INotifyObserver> 	subscribers	  	= new ArrayList<INotifyObserver>();
	private List<Period> 			periods	  		= new ArrayList<Period>();

	private INotifyObserver		subscriber1;
	private INotifyObserver		subscriber2;

	private CostFree			policy;
	private Property			property;
	private Pricer				pricer;
	private Period				period1;
	private Period				period2;
	private Period				period3;
	private Owner				owner;
	private Tenant				tenant1;
	private Tenant				tenant2;
	
	private PaymentMethod		paymentMethod1;
	private PaymentMethod		paymentMethod2;

	private LocalDate 				checkIn;
	private LocalDate				checkOut;
//	private List<PaymentMethod>		allowedPaymentMethods;
	private double 					pricePerDayWeekday;
	private double 					pricePerDayHighSeason;
	private double 					pricePerDayLongWeekend;

	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateAvailable		= spy(ReserveAvailable.class);
		this.stateApproved		= mock(ReserveApproved.class);
		this.stateCompleted		= mock(ReserveCompleted.class);

		this.policy				= spy(CostFree.class);
		this.property			= mock(Property.class);
		this.pricer				= mock(Pricer.class);
		this.period1			= mock(Period.class);
		this.period2			= mock(Period.class);
		this.period3			= mock(Period.class);
		this.owner				= mock(Owner.class);
		this.tenant1 	  		= mock(Tenant.class);
		this.tenant2 	  		= mock(Tenant.class);
		this.paymentMethod1 	= mock(PaymentMethod.class);
		this.paymentMethod2 	= mock(PaymentMethod.class);
		this.subscriber1	  	= mock(INotifyObserver.class);
		this.subscriber2	  	= mock(INotifyObserver.class);

		this.periods.add(period1);
		this.periods.add(period2);
		this.periods.add(period3);
		this.paymentMethods.add(paymentMethod1);
		this.paymentMethods.add(paymentMethod2);
		this.subscribers.add(subscriber1);
		this.subscribers.add(subscriber2);

		this.checkIn			= LocalDate.of(2024, 11, 30);
		this.checkOut			= LocalDate.of(2024, 12, 30);
//		this.allowedPaymentMethods // se repite?
		this.pricePerDayWeekday		= 5;
		this.pricePerDayHighSeason	= 6;
		this.pricePerDayLongWeekend	= 7;

		when(this.stateAvailable.next()).thenReturn(stateApproved);
		when(this.stateApproved.next()).thenReturn(stateCompleted);
		when(this.stateCompleted.next()).thenReturn(stateAvailable);
		when(this.pricer.price(this.checkIn)).thenReturn(pricePerDayWeekday);
		when(this.pricer.priceBetween(this.checkIn, this.checkOut)).thenReturn(pricePerDayWeekday+pricePerDayHighSeason+pricePerDayLongWeekend);
		when(this.property.getOwner()).thenReturn(this.owner);
		
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
									, subscribers
									, waitingTenants );

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
	public void testGetProperty() {
		assertEquals(this.property, this.booking.getProperty());
	}

	@Test
	public void testSetState() {
		assertNotNull(this.booking.getState());
		this.booking.setState(this.stateApproved);
		assertEquals(this.stateApproved, this.booking.getState());
	}
	
	@Test
	public void testFromAvailableToApproved() {
//		this.booking.setState(this.stateAvailable);
		assertEquals(this.stateAvailable, this.booking.getState());
//		this.booking.setState(this.stateApproved);
		IReserveState expectedState = this.stateAvailable.next();
		assertEquals(expectedState, this.stateApproved);
		this.booking.setState(this.booking.getState().next());
		assertEquals(this.stateApproved, this.booking.getState());
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
		this.booking.applyPolicy();
		verify(this.policy, times(1)).activate();
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
		verifyNoInteractions(this.stateAvailable);
		verifyNoInteractions(this.property);
		verifyNoInteractions(this.owner);
		assertTrue(this.waitingTenants.isEmpty());
		this.booking.reserve(this.tenant1);
		assertTrue(this.waitingTenants.isEmpty());
		verify(this.stateAvailable, times(1)).requestReserve(this.booking);
		verify(this.property, times(1)).getOwner();
		verify(this.owner, times(1)).reserveRequestedOn(this.booking);
	}

	@Test
	public void testApproveReserve() {
		verifyNoInteractions(this.stateAvailable);
		verify(this.stateAvailable, times(0)).requestReserve(this.booking);
		this.booking.reserve(this.tenant1);
		verify(this.stateAvailable, times(1)).requestReserve(this.booking);
		verify(this.stateAvailable, times(0)).approveReserve(this.booking);
		verify(this.stateAvailable, times(0)).next();
		assertEquals(this.stateAvailable, this.booking.getState());
		this.booking.approveReserve();
		verify(this.stateAvailable, times(1)).approveReserve(this.booking);
		verify(this.stateAvailable, times(1)).next();
		assertEquals(this.stateAvailable.next(), this.booking.getState());
	}
	
	@Test
	public void testCancelReserve() {
		verifyNoInteractions(this.stateAvailable);
		verify(this.stateAvailable, times(0)).cancelReserve(this.booking);
		assertEquals(this.stateAvailable, this.booking.getState());
		this.booking.reserve(this.tenant1);
		this.booking.cancelReserve();
		verify(this.stateAvailable, times(1)).cancelReserve(this.booking);
		assertEquals(this.stateAvailable, this.booking.getState());
		assertNull(this.booking.getTenant());
	}

	@Test
	public void testTriggerNextRequest() {
		verifyNoInteractions(this.stateAvailable);
		assertEquals(this.stateAvailable, this.booking.getState());
		verify(this.stateAvailable, times(0)).requestReserve(this.booking);
		assertTrue(this.waitingTenants.isEmpty());
		this.booking.reserve(this.tenant1);
		verify(this.stateAvailable, times(1)).requestReserve(this.booking);
		this.booking.reserve(this.tenant2);
		assertEquals(1, this.waitingTenants.size());
		assertEquals(this.stateAvailable, this.booking.getState());
		assertTrue(this.waitingTenants.containsAll(Arrays.asList(this.tenant2)));
		verify(this.stateAvailable, times(1)).requestReserve(this.booking);
		this.booking.cancelReserve();
		assertEquals(0, this.waitingTenants.size());
		verify(this.stateAvailable, times(2)).requestReserve(this.booking);
	}

	@Test
	public void testRegisterObserver() {
		assertEquals(2, this.subscribers.size());
		INotifyObserver subscriber3 = mock(INotifyObserver.class);
		this.booking.registerObserver(subscriber3);
		assertEquals(3, this.subscribers.size());
	}

	@Test
	public void testUnregisterObserver() {
		assertEquals(2, this.subscribers.size());
		this.booking.unregisterObserver(this.subscriber1);
		assertEquals(1, this.subscribers.size());
		
	}

	@Test
	public void testUpdateObservers() {
		verifyNoInteractions(this.subscriber1);
		verifyNoInteractions(this.subscriber2);
		this.booking.updateObservers();
		verify(this.subscriber1, times(1)).update(this.booking);
		verify(this.subscriber2, times(1)).update(this.booking);
	}
}
