package sa.booking;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Pricer {
	
	private double basePrice;
	private List<SpecialPeriod> specialPeriods;

	public Pricer(double pricePerDayWeekday, List<SpecialPeriod> periods) {
		// TODO Auto-generated constructor stub
		this.basePrice = pricePerDayWeekday;
		this.specialPeriods = periods;
	}

	public void addSpecialPeriod(SpecialPeriod p) {
		// TODO Auto-generated method stub
		this.specialPeriods.add(p);
	}

	public void setBasePrice(double pricePerDayWeekday) {
		// TODO Auto-generated method stub
		this.basePrice = pricePerDayWeekday;
	}

	public double price(LocalDate date) {
		// TODO Auto-generated method stub
		Optional<SpecialPeriod> sp = this.specialPeriods.stream()
									.filter(p -> p.belongs(date))
									.findFirst();
		return sp.isPresent() ? sp.get().price() : this.basePrice;
	}

	public double priceBetween(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		double totalPrice = 0;
		LocalDate currentDate = startDate;
		while (!currentDate.equals(endDate)) {
			totalPrice += this.price(currentDate);
			currentDate = currentDate.plusDays(1);
		}
		return totalPrice + this.price(currentDate);		
	}

	public void removeSpecialPeriod(SpecialPeriod p) {
		// TODO Auto-generated method stub
		this.specialPeriods.remove(p);
	}

}
