package unq.po2.tpFinal;

import java.util.Set;

public class BookingConfirmedStrategy implements BookingStrategy {
	private Set<Booking> confirmedBookings;

    public BookingConfirmedStrategy(Set<Booking> confirmedBookings) {
        this.confirmedBookings = confirmedBookings;
    }

    public void process(Booking booking) {
        confirmedBookings.add(booking);
    }
}
