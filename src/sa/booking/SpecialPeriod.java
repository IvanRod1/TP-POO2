package sa.booking;

import java.time.LocalDate;

public class SpecialPeriod {

	private double		price;
	private LocalDate	begin;
	private LocalDate	end;
	
	public SpecialPeriod(double price, LocalDate begin, LocalDate end) {
		// TODO Auto-generated constructor stub
		this.price	= price;
		this.begin	= begin;
		this.end	= end;
	}

	public boolean belongs(LocalDate date) {
		// TODO Auto-generated method stub
		return date.isEqual(this.start()) 	||
			   date.isEqual(this.end()) 	|| 
			   (date.isAfter(this.start()) 	&& date.isBefore(this.end()));
	}

	public double price() {
		// TODO Auto-generated method stub
		return this.price;
	}

	public LocalDate start() {
		// TODO Auto-generated method stub
		return this.begin;
	}

	public LocalDate end() {
		// TODO Auto-generated method stub
		return this.end;
	}

}
