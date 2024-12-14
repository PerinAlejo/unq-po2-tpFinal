package unq.po2.tpFinal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import unq.po2.tpFinal.implementations.BookingEmailSender;
import unq.po2.tpFinal.implementations.EmailSenderImpl;

public class BookingStatus {
	private List<Booking> pendingBookings;
	private List<Booking> confirmedBooking;
	private List<Booking> waitlist;

	public BookingStatus() {
		this.pendingBookings = new ArrayList<Booking>();
		this.confirmedBooking = new ArrayList<Booking>();
		this.waitlist = new ArrayList<Booking>();
	}

	public boolean isAvailable(DateRange range) {
		return this.confirmedBooking.stream().noneMatch(booking -> booking.isBookedOnRange(range));
	}

	public void book(Booking booking, Housing housing) {
		this.pendingBookings.remove(booking);
		if (this.isAvailable(booking.getRange())) {
			this.confirmedBooking.add(booking);
			housing.markAsBooked(booking);
		} else {
			this.waitlist.add(booking);			
		}
	}

	public void cancelBooking(Booking booking, Housing housing) {
		confirmedBooking.remove(booking);
		Optional<Booking> onWait = this.waitlist.stream()
				.filter(wait -> wait.isBookedOnRange(booking.getRange()) && this.isAvailable(wait.getRange()))
				.findFirst();
		if (onWait.isPresent()) {
			Booking next = onWait.get();
			this.waitlist.remove(next);
			this.addPendingBooking(next);			
		}
		housing.bookingIsCancelled(booking);
	}

	public List<Booking> getBookings() {
		return this.confirmedBooking;
	}

	public void addPendingBooking(Booking booking) {
		this.pendingBookings.add(booking);
		booking.getHousing().addObserver(new BookingEmailSender(new EmailSenderImpl()));
	}
}
