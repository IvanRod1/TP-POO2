package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Pricer {
	private double basePrice;
	private List<Period> specialPeriods;

	public Pricer(double pricePerDayWeekday, List<Period> periods) {
		// TODO Auto-generated constructor stub
		this.basePrice = pricePerDayWeekday;
		this.specialPeriods = periods;
	}

	public void addSpecialPeriod(Period p) {
		// TODO Auto-generated method stub
		this.specialPeriods.add(p);
	}

	public void setBasePrice(double pricePerDayWeekday) {
		// TODO Auto-generated method stub
		this.basePrice = pricePerDayWeekday;
	}

	public double price(LocalDate date) throws NoSuchElementException {
		// TODO Auto-generated method stub
		try {
			return this.specialPeriods.stream()
				.filter(p -> p.belongs(date))
				.findFirst()
				.get()
				.price();
		} catch (NoSuchElementException e) {
			return this.basePrice;
		}
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

}
