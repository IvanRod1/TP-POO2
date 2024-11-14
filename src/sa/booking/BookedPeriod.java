package sa.booking;

import java.time.LocalDate;

import sa.users.Tenant;

public class BookedPeriod {

	LocalDate 		start;
	LocalDate 		end;
	Tenant			tenant;
	
	public BookedPeriod(Tenant t, LocalDate start, LocalDate end) {
		this.start  = start;
		this.end    = end;
		this.tenant = t;
	}
	
	public LocalDate start() {return this.start;}
	public LocalDate end() {return this.end;}
	public Tenant tenant() {return this.tenant;}

	public boolean belongs(LocalDate date) {
		return date.isEqual(this.start()) 	||
				   date.isEqual(this.end()) 	|| 
				   (date.isAfter(this.start()) 	&& date.isBefore(this.end()));
	}

	public boolean equals(BookedPeriod b) {
		return this.tenant().equals(b.tenant()) && 
				this.start().equals(b.start())  &&
				this.end().equals(b.end());
	}
}
