package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Pricer {
	private double basePrice;
	private double specialPrice;
	private List<LocalDate> specialPeriods;

	public Pricer(double pricePerDayWeekday, List<LocalDate> dates) {
		// TODO Auto-generated constructor stub
		this.basePrice = pricePerDayWeekday;
		this.specialPeriods = dates;
	}

	public void addSpecialPeriod(LocalDate d) {
		// TODO Auto-generated method stub
		this.specialPeriods.add(d);
	}

	public void setBasePrice(double pricePerDayWeekday) {
		// TODO Auto-generated method stub
		this.basePrice = pricePerDayWeekday;
	}

//	public double price(LocalDate date) throws NoSuchElementException {
//		// TODO Auto-generated method stub
//		try {
//			return this.specialPeriods.stream()
//				.filter(p -> p.belongs(date))
//				.findFirst()
//				.get()
//				.price();
//		} catch (NoSuchElementException e) {
//			return this.basePrice;
//		}
//	}

//	public double priceBetween(LocalDate startDate, LocalDate endDate) {
//		// TODO Auto-generated method stub
//		double totalPrice = 0;
//		LocalDate currentDate = startDate;
//		while (!currentDate.equals(endDate)) {
//			totalPrice += this.price(currentDate);
//			currentDate = currentDate.plusDays(1);
//		}
//		return totalPrice + this.price(currentDate);		
//	}

	public double getBasePrice() {
		// TODO Auto-generated method stub
		return this.basePrice;
	}

	public double getPrice(LocalDate date) {
		if(this.specialPeriods.contains(date)) {
			return this.specialPrice;
		}
		else {
			return this.basePrice;
		}
	}

}
