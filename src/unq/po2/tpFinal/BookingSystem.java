package unq.po2.tpFinal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingSystem implements BookingAcceptedObserver {		
	
	private Set<Booking> bookings;
	public List<BookingCancelledObserver> bookingCancelledObservers;
	
	public BookingSystem(List<BookingCancelledObserver> bookingCancelledObserver) {
		
		bookings = new HashSet<Booking>();
		this.bookingCancelledObservers = bookingCancelledObserver;
	}

	@Override
	public void notifyBookingAccepted(Booking booking) {		
		//si la propiedad está ocupada, se agrega a la cola de espera
        if (booking.getHousing().isOccupied(booking.getRange())) {
            booking.getHousing().addToWaitlist(booking);
        } else {
            booking.getHousing().addBooking(booking);
            booking.getHousing().getOwner().accept(booking);
        }	
	}
	
	public void cancelBooking(Booking booking) {
        this.bookings.remove(booking);
        this.bookingCancelledObservers.forEach(observer -> observer.notifyBookingCancelled(booking));
        booking.cancelBook();
        //proceso la waitlist
        booking.getHousing().processWaitlist();
    }
	
	public List<Booking> getAllBookings(Tenant tenant){
		return bookings.stream()
				.filter(booking-> booking.getTenant().equals(tenant))
				.toList();
	}
	
	public List<Booking> getAllBookings(){
		return bookings.stream().toList();
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