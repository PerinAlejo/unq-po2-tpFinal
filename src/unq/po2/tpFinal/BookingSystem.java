package unq.po2.tpFinal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingSystem implements BookingAcceptedObserver {
		
	private Set<Booking> unconfirmedBookings;
	private Set<Booking> confirmedBookings;
	public List<BookingCancelledObserver> bookingCancelledObservers;
	
	
	public BookingSystem(List<BookingCancelledObserver> bookingCancelledObserver) {
		unconfirmedBookings = new HashSet<Booking>();
		confirmedBookings = new HashSet<Booking>();
		this.bookingCancelledObservers = bookingCancelledObserver;
	}

	@Override
	public void notifyBookingAccepted(Booking booking) {		
		confirmedBookings.add(booking);
		unconfirmedBookings.remove(booking);
	}
	
	public void cancelBooking(Booking booking) {
		this.confirmedBookings.remove(booking);
		this.bookingCancelledObservers.forEach(observer -> observer.notifyBookingCancelled(booking));
	}
	
	public List<Booking> getAllBookings(Tenant tenant){
		return Stream.concat(this.getUnconfirmedBookings(tenant), this.getConfirmedBookings(tenant))
			    .collect(Collectors.toList());
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
	
	private Stream<Booking> getUnconfirmedBookings(Tenant tenant){
		return unconfirmedBookings.stream()
				.filter(booking -> booking.getTenant().equals(tenant));
	}
	
	
	private Stream<Booking> getConfirmedBookings(Tenant tenant){
		return unconfirmedBookings.stream()
				.filter(booking -> booking.getTenant().equals(tenant));
	}
}