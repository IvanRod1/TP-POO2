package sa.administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sa.accomodationsite.AccomodationSite;
import sa.accomodationsite.Administrator;
import sa.accomodationsite.Booking;
import sa.accomodationsite.ReserveApproved;
import sa.accomodationsite.ReserveAvailable;
import sa.accomodationsite.ReserveState;
import sa.properties.Property;
import sa.user.Tenant;


	


class AdministratorTest {

	Administrator administrator;
	AccomodationSite accomodationSite;
	String fullName;
	int telephone;
	String mail;
	
	Tenant tenant1;
	Tenant tenant2;
	
	List<Tenant> tenants;
	
	Booking booking1;
	Booking booking2;
	Booking booking3;
	Booking booking4;
	Booking booking5;
	Booking booking6;

	List<Tenant> topTenants;
	
	Property property1;
	Property property2;
	Property property3;
	
	List<Property> availableProperties;
	
	ReserveState reserveAvailable1;
	ReserveState reserveAvailable2;
	ReserveState reserveAvailable3;
	
	ReserveState reserveApproved1;
	ReserveState reserveApproved2;
	
	List<Booking> approvedBookings;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		// acá todo lo relacionado a accomodationSite:
		
		accomodationSite = spy(new AccomodationSite());
		
		tenant1 = mock(Tenant.class);
		tenant2 = mock(Tenant.class);
		
		booking1 = mock(Booking.class);
		booking2 = mock(Booking.class);
		booking3 = mock(Booking.class);
		booking4 = mock(Booking.class);
		booking5 = mock(Booking.class);
		booking6 = mock(Booking.class);
		
		property1 = mock(Property.class);
		property2 = mock(Property.class);
		property3 = mock(Property.class);
		
		accomodationSite.addBooking(booking1);
		accomodationSite.addBooking(booking2);
		accomodationSite.addBooking(booking3);
		
		
		reserveAvailable1 = mock(ReserveAvailable.class);
		reserveAvailable2 = mock(ReserveAvailable.class);
		reserveAvailable3 = mock(ReserveAvailable.class);
		
		reserveApproved1 = mock(ReserveApproved.class);
		reserveApproved2 = mock(ReserveApproved.class);
		
		approvedBookings = new ArrayList<Booking>();
		approvedBookings.add(booking4);
		approvedBookings.add(booking5);
		approvedBookings.add(booking6);
	
		// configuro stubs de booking:
		
		when(booking1.getTenant()).thenReturn(tenant1);
		when(booking2.getTenant()).thenReturn(tenant2);
		when(booking3.getTenant()).thenReturn(tenant1);
		
		when(booking1.getProperty()).thenReturn(property1);
		when(booking2.getProperty()).thenReturn(property2);
		when(booking3.getProperty()).thenReturn(property3);
		
		when(booking1.getState()).thenReturn(reserveAvailable1);
		when(booking2.getState()).thenReturn(reserveAvailable2);
		when(booking3.getState()).thenReturn(reserveAvailable3);
		
		when(booking4.getState()).thenReturn(reserveApproved1);
		when(booking5.getState()).thenReturn(reserveApproved2);
		when(booking6.getState()).thenReturn(reserveApproved2);
		
		when(accomodationSite.getApprovedBookings()).thenReturn(approvedBookings);
		
		
		
		
		// acá todo lo relacionado a Administrator:
		fullName = "Nacho";
		telephone = 123;
		mail = "foo@gmail.com";
		
		
		
		// SUT: Administrator
		
		administrator = new Administrator(fullName, telephone, mail, accomodationSite);
		
		tenants = new ArrayList<Tenant>();
		
		tenants.add(tenant1);
		tenants.add(tenant2);
		
		topTenants = new ArrayList<Tenant>();
		
		topTenants.add(tenant1);
		topTenants.add(tenant2);
		
		availableProperties = new ArrayList<Property>();
		availableProperties.add(property1);
		availableProperties.add(property2);
		availableProperties.add(property3);
		
		
		
	}
	
	

	// Excersise:
	
	@Test
	void bestTenantsTest() {
		
		assertEquals(administrator.bestTenants(tenants), topTenants);
		//assertEquals(1,1);
	}
	
	
	@Test
	void propertiesToBeReservedTest() {
		
		assertEquals(administrator.propertiesToBeReserve(), availableProperties);
		
		
	}
	
	@Test
	void occupancyRateTest() {
		
		assertEquals(administrator.occupancyRate(), 100.0);
	}
	
	
	
	
	
	
	

}













































