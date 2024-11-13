package unq.po2.tpFinal.implementations;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.BookingStrategy;
import unq.po2.tpFinal.domain.Housing;

public class BookingConditionalStrategy implements BookingStrategy{
	private Map<Housing, Queue<Booking>> conditionalBookings;

    public BookingConditionalStrategy(Map<Housing, Queue<Booking>> conditionalBookings) {
        this.conditionalBookings = conditionalBookings;
    }

    public void process(Booking booking) {
        conditionalBookings
            .computeIfAbsent(booking.getHousing(), k -> new LinkedList<>())
            .add(booking);
    }
}
