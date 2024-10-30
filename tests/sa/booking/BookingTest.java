package sa.booking;

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
import sa.properties.Property;
import sa.subscriptions.INotifyObserver;
import sa.users.Owner;
import sa.users.Tenant;


public class BookingTest {

	private Booking				booking;
	private ReserveAvailable	stateAvailable;
	private ReserveApproved		stateApproved;
	private ReserveCompleted	stateCompleted;
	
	private List<Tenant> 			tenants 	  	= new ArrayList<Tenant>();
	private List<PaymentMethod> 	paymentMethods 	= new ArrayList<PaymentMethod>();
	private List<INotifyObserver> 	subscribers	  	= new ArrayList<INotifyObserver>();

	private INotifyObserver		subscriber1;
	private INotifyObserver		subscriber2;

	private Property			property;
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
		
		this.property			= mock(Property.class);
		this.owner				= mock(Owner.class);
		this.tenant1 	  		= mock(Tenant.class);
		this.tenant2 	  		= mock(Tenant.class);
		this.paymentMethod1 	= mock(PaymentMethod.class);
		this.paymentMethod2 	= mock(PaymentMethod.class);
		this.subscriber1	  	= mock(INotifyObserver.class);
		this.subscriber2	  	= mock(INotifyObserver.class);

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
		
		// SUT (System Under Test): objeto a testear
		this.booking = new Booking(   stateAvailable
									, property
									, checkIn
									, checkOut
									, paymentMethods
									, pricePerDayWeekday
									, pricePerDayHighSeason
									, pricePerDayLongWeekend );
	}

	@Test
	public void testConstructor() {
		assertNotNull(this.booking);
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
	
}
