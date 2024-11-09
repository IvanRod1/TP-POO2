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
import java.util.List;

import sa.payments.PaymentMethod;
import sa.policies.CostFree;
import sa.policies.ICancellationPolicy;
import sa.properties.Property;
import sa.subscriptions.INotifyObserver;
import sa.users.Owner;
import sa.users.Tenant;


public class BookingTest {

	private Booking				booking;
	private Booking				bookingReal;
	private ReserveAvailable	stateAvailable;
	private ReserveApproved		stateApproved;
	private ReserveCompleted	stateCompleted;
	
	private List<Tenant> 			tenants 	  	= new ArrayList<Tenant>();
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

	private Integer					bookingCounter;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.stateAvailable		= mock(ReserveAvailable.class);
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
		this.tenants.add(tenant2);
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
		this.bookingCounter			= 0;

		when(this.stateAvailable.next()).thenReturn(stateApproved);
		when(this.stateApproved.next()).thenReturn(stateCompleted);
		when(this.stateCompleted.next()).thenReturn(stateAvailable);
		when(this.pricer.price(this.checkIn)).thenReturn(pricePerDayWeekday);
		when(this.pricer.priceBetween(this.checkIn, this.checkOut)).thenReturn(pricePerDayWeekday+pricePerDayHighSeason+pricePerDayLongWeekend);
		
		// SUT (System Under Test): objeto a testear
		this.booking = new Booking(   stateAvailable
									, pricer
									, property
									, checkIn
									, checkOut
									, paymentMethods
									, pricePerDayWeekday
									, periods );

//		this.bookingReal = new Booking(   property
//										, checkIn
//										, checkOut
//										, paymentMethods
//										, pricePerDayWeekday
//										, periods );
	}

	@Test
	public void testConstructor() {
		assertNotNull(this.booking);
	}

//	@Test
//	public void testConstructorReal() {
//		assertNotNull(this.bookingReal);
//	}

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
		assertNull(this.booking.getPolicy());
		verifyNoInteractions(this.policy);
		this.booking.setCancellationPolicy((ICancellationPolicy) this.policy);
		assertEquals(this.policy, this.booking.getPolicy());
		verifyNoInteractions(this.policy);
	}

	@Test
	public void testApplyPolicy() {
		assertNull(this.booking.getPolicy());
		verifyNoInteractions(this.policy);
		this.booking.setCancellationPolicy((ICancellationPolicy) this.policy);
		this.booking.applyPolicy();
		verify(this.policy).activate();
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
	public void testCancelReserve() {
		ReserveAvailable spyState = mock(ReserveAvailable.class);
		this.booking.setState(spyState);
		assertEquals(spyState, this.booking.getState());
		verify(spyState, times(0)).cancelReserve();
		this.booking.cancelReserve();
		verify(spyState, times(1)).cancelReserve();
	}

	@Test
	public void testReserve() {
		ReserveAvailable spyState = mock(ReserveAvailable.class);
		this.booking.setState(spyState);
		assertEquals(spyState, this.booking.getState());
		verify(spyState, times(0)).requestReserve(this.tenant1);
		this.booking.reserve(this.tenant1);
		verify(spyState, times(1)).requestReserve(this.tenant1);
	}
}
