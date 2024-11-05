package sa.accomodationsite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sa.properies.PaymentMethodEnum;
import sa.properies.Property;
import sa.user.Tenant;

public class Booking {
	Property property;
	LocalDate checkIn;
	LocalDate checkOut;
	List<PaymentMethodEnum> paymentMethods;
	double pricePerDayWeekday;
	double pricePerDayHighSeason;
	double pricePerDayLongSeason;
	ReserveState state;
	List<Tenant> tenants;
	Tenant tenant;
	
	public Booking(Property property, LocalDate checkIn, LocalDate checkOut, double pricePerDayWeekday,
				   double pricePerDayHighSeason, double pricePerDayLongSeason, ReserveState state, Tenant tenant) {
		this.property = property;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		paymentMethods = new ArrayList<PaymentMethodEnum>();
		this.pricePerDayWeekday = pricePerDayWeekday;
		this.pricePerDayHighSeason = pricePerDayHighSeason;
		this.pricePerDayLongSeason = pricePerDayLongSeason;
		this.state = state;
		this.tenants = new ArrayList<Tenant>();
		this.tenant = tenant;
		
	}
	
	public Property getProperty() {
		return this.property;
	}
	
	public ReserveState getState() {
		return this.state;
	}
	
	public Tenant getTenant() {
		return this.tenant;
	}
	
	public List<Tenant> getTenants() {
		return this.tenants;
	}
	
	public LocalDate getCheckIn() {
		return this.checkIn;
	}
	

}
