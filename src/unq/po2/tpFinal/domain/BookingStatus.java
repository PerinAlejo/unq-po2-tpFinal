package unq.po2.tpFinal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingStatus {
	private List<Booking> bookings;
	private List<Booking> waitlist;

	public BookingStatus() {
		this.bookings = new ArrayList<Booking>();
		this.waitlist = new ArrayList<Booking>();
	}
	
	public boolean isAvailable(DateRange range) {
		return this.bookings.stream().noneMatch(booking -> booking.isBookedOnRange(range));
	}

	public void book(Booking booking, Housing housing) {
		if (this.isAvailable(booking.getRange())) {
			this.bookings.add(booking);
			housing.markAsBooked(booking);
		} else {
			this.waitlist.add(booking);
		}
	}

	public void cancelBooking(Booking booking, Housing housing) {
		bookings.remove(booking);
		Optional<Booking> onWait = this.waitlist.stream()
				.filter(wait -> wait.isBookedOnRange(booking.getRange()) && this.isAvailable(wait.getRange()))
				.findFirst();
		if (onWait.isPresent()) {
			Booking next = onWait.get();
			this.waitlist.remove(next);
			this.bookings.add(next);
			housing.markAsBooked(next);
		}
		housing.bookingIsCancelled(booking);
	}
}
