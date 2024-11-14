package sa.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sa.users.Tenant;

public class BookingSchedule {

	private HashMap<LocalDate, List<BookedPeriod>>		bookedPeriods;
	
	public BookingSchedule() {
		this.bookedPeriods = new HashMap<LocalDate, List<BookedPeriod>>();
	}

	// Para testear DOC
	public BookingSchedule(HashMap<LocalDate, List<BookedPeriod>> bookedPeriods) {
		// TODO Auto-generated constructor stub
		this.bookedPeriods = bookedPeriods;
	}

	public List<BookedPeriod> reserves(LocalDate date) {
		// TODO Auto-generated method stub
		return this.getBPs().get(date);
	}

	public boolean hasReserves(LocalDate date) {
		// TODO Auto-generated method stub
		return this.getBPs().containsKey(date) && this.reserves(date).size() != 0;
	}

	public void reserve(Tenant t, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		BookedPeriod bp = this.newBP(t, start, end);
		if (this.getBPs().containsKey(start)) {
			this.getBPs().get(start).add(bp); // Lista orden de llegada
		} else {
			List<BookedPeriod> bps = new ArrayList<BookedPeriod>();
			bps.add(bp);
			this.getBPs().put(start, bps);
		}
	}

	public BookedPeriod getNext(LocalDate date) {
		return this.getBPs().get(date).removeFirst();		
	}

	public HashMap<LocalDate, List<BookedPeriod>> getBPs() {
		return this.bookedPeriods;		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.getBPs().isEmpty();
	}
	
	public BookedPeriod newBP(Tenant t, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		return new BookedPeriod(t, start, end);
	}

	public void remove(BookedPeriod bp) {
		// TODO Auto-generated method stub
		this.reserves(bp.start()).remove(bp);
		if (!this.hasReserves(bp.start())) {
			this.getBPs().remove(bp.start());
		}
	}

}
