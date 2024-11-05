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

	List<Tenant> topTenants;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		// acá todo lo relacionado a accomodationSite:
		
		accomodationSite = spy(new AccomodationSite());
		
		tenant1 = mock(Tenant.class);
		tenant2 = mock(Tenant.class);
		
		booking1 = mock(Booking.class);
		booking2 = mock(Booking.class);
		booking3 = mock(Booking.class);
		
		accomodationSite.addBooking(booking1);
		accomodationSite.addBooking(booking2);
		accomodationSite.addBooking(booking3);
	
		// configuro stubs de booking:
		
		when(booking1.getTenant()).thenReturn(tenant1);
		when(booking2.getTenant()).thenReturn(tenant2);
		when(booking3.getTenant()).thenReturn(tenant1);
		
		
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
		
		
		
	}
	
	

	// Excersise:
	
	@Test
	void bestTenantsTest() {
		
		assertEquals(administrator.bestTenants(tenants), topTenants);
		//assertEquals(1,1);
	}

}
