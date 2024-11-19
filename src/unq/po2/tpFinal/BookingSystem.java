package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.interfaces.HousingObserver;

public class BookingSystem implements HousingObserver {

	private Set<Booking> confirmedBookings;

	public BookingSystem() {

		confirmedBookings = new HashSet<Booking>();
	}

	public List<Booking> getAllBookings(Tenant tenant) {
		return confirmedBookings.stream().filter(booking -> booking.getTenant().equals(tenant)).toList();
	}

	public List<Booking> getAllBookings() {
		return confirmedBookings.stream().toList();
	}

	public List<Booking> getFutureBookings(Tenant tenant) {
		return this.getAllBookings(tenant).stream().filter(booking -> booking.startsAfter(LocalDate.now())).toList();
	}

	public List<Booking> getBookingsFromCity(Tenant tenant, City city) {
		return this.getAllBookings(tenant).stream().filter(booking -> booking.isOnCity(city)).toList();
	}

	public List<City> getBookingCities(Tenant tenant) {
		return this.getAllBookings(tenant).stream().map(booking -> booking.getCity()).toList();
	}

	@Override
	public void notifyBookingAccepted(Housing housing, Booking booking) {
		this.confirmedBookings.add(booking);
	}

	@Override
	public void notifyBookingCancelled(Housing housing, Booking booking) {
		this.confirmedBookings.remove(booking);
	}

	@Override
	public void notifyPriceDrop(Housing housing, double newPrice) {
		
	}
}