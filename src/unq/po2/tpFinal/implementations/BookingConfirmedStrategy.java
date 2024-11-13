package unq.po2.tpFinal.implementations;

import java.util.Set;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.interfaces.BookingStrategy;

public class BookingConfirmedStrategy implements BookingStrategy {
	private Set<Booking> confirmedBookings;

	public BookingConfirmedStrategy(Set<Booking> confirmedBookings) {
		this.confirmedBookings = confirmedBookings;
	}

	public void process(Booking booking) {
		confirmedBookings.add(booking);
	}
}
