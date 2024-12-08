package unq.po2.tpFinal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.implementations.BookingConditionalStrategy;
import unq.po2.tpFinal.implementations.BookingConfirmedStrategy;
import unq.po2.tpFinal.interfaces.BookingAcceptedObserver;
import unq.po2.tpFinal.interfaces.BookingCancelledObserver;
import unq.po2.tpFinal.interfaces.BookingStrategy;
import unq.po2.tpFinal.implementations.EventPublisherImpl;

import java.util.*;

public class BookingSystem implements BookingAcceptedObserver {		
	
	private Set<Booking> confirmedBookings;
	private Map<Housing, Queue<Booking>> conditionalBookings;
	public List<BookingCancelledObserver> bookingCancelledObservers;
	public List<BookingAcceptedObserver> bookingAcceptedObservers;
	private EventPublisherImpl eventPublisher;
	
	public BookingSystem(List<BookingCancelledObserver> bookingCancelledObserver, List<BookingAcceptedObserver> bookingAcceptedObserver, EventPublisherImpl eventPublisher) {
		
		confirmedBookings = new HashSet<Booking>();
		conditionalBookings = new HashMap<>();
		this.bookingCancelledObservers = bookingCancelledObserver;
		this.bookingAcceptedObservers = bookingAcceptedObserver;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void notifyBookingAccepted(Booking booking) {
		processBooking(booking);		
	}
	
	public void processBooking(Booking booking) {
        BookingStrategy strategy;

        if (isHousingAvailable(booking)) {
            strategy = new BookingConfirmedStrategy(confirmedBookings);
            this.eventPublisher.notifyReservationAccepted(booking);
        } else {
            strategy = new BookingConditionalStrategy(conditionalBookings);
        }

        strategy.process(booking);
    }
	
	private boolean isHousingAvailable(Booking booking) {
        return confirmedBookings.stream()
            .noneMatch(b -> b.getHousing().equals(booking.getHousing()) &&
                            b.getRange().overlaps(booking.getRange()));
    }
	
	public void cancelBooking(Booking booking) {
		this.confirmedBookings.remove(booking);
		this.bookingCancelledObservers.forEach(observer -> observer.notifyBookingCancelled(booking));
		this.eventPublisher.notifyReservationCancelled(booking.getHousing().getHousingType().getName());
		booking.cancelBook();
		
		//si existe proceso la siguiente reserva condicional en la cola
        Queue<Booking> waitlist = conditionalBookings.get(booking.getHousing());
        if (waitlist != null && !waitlist.isEmpty()) {
            Booking nextBooking = waitlist.poll();
            new BookingConfirmedStrategy(confirmedBookings).process(nextBooking);
            notifyObserversBookingAccepted(nextBooking);
            booking.acceptBook();
        }
	}
	
	private void notifyObserversBookingAccepted(Booking booking) {
	    this.bookingAcceptedObservers.forEach(observer -> observer.notifyBookingAccepted(booking));
	}
	
	public List<Booking> getAllBookings(Tenant tenant){
		return confirmedBookings.stream()
				.filter(booking-> booking.getTenant().equals(tenant))
				.toList();
	}
	
	public List<Booking> getAllBookings(){
		return confirmedBookings.stream().toList();
	}
	
	public List<Booking> getFutureBookings(Tenant tenant){
		return this.getAllBookings(tenant)
				.stream()
				.filter(booking -> booking.startsAfter(LocalDate.now()))
				.toList();
	}
	
	public List<Booking> getBookingsFromCity(Tenant tenant, City city){
		return this.getAllBookings(tenant)
				.stream()
				.filter(booking -> booking.isOnCity(city))
				.toList();
	}
	
	public List<City> getBookingCities(Tenant tenant){
		return this.getAllBookings(tenant)
				.stream()
				.map(booking-> booking.getCity())
				.toList();
	}	
}