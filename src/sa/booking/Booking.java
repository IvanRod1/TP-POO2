package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import sa.paymentMethod.PaymentMethod;
import sa.properties.Property;
import sa.observer.interfaces.*;
import sa.users.Tenant;

public class Booking implements INotifyConfiguration {

	private LocalDate				checkIn;
	private LocalDate				checkOut;
	private Property				property;
	private IReserveState			state;
	//private ICancellationPolicy		policy;
	private Pricer					pricer;
	private List<PaymentMethod>		paymentMethods;
	
	
	
	public IReserveState getState() {
		// TODO Auto-generated method stub
		return this.state;
	}



	public LocalDate getCheckIn() {
		// TODO Auto-generated method stub
		return this.checkIn;
	}



	public LocalDate getCheckOut() {
		// TODO Auto-generated method stub
		return this.checkOut;
	}



	public Pricer getPricer() {
		// TODO Auto-generated method stub
		return this.pricer;
	}



	public void updateObservers() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void registerObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void unregisterObserver(INotifyObserver o) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}