package sa.observer;

import java.time.LocalDate;

import sa.booking.Booking;
import sa.booking.Reserve;

public class BookingSubscriber {
	
	private Booking 	booking;
	private Reserve 	reserve;
	private LocalDate	date;
	
	public BookingSubscriber(Booking b, Reserve r, LocalDate d) {
		this.booking = b;
		this.reserve = r;
		this.date    = d;
	}

	public Booking getBooking() {
		return booking;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public LocalDate getDate() {
		return date;
	}


	
}
