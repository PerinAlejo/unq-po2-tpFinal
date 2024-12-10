package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Tenant;

public class BookingSystem {

	private List<Housing> housings;

	public BookingSystem(List<Housing> housings) {

		this.housings = housings;
	}

	public List<Booking> getAllBookings(Tenant tenant) {
		return this.getAllBookings().stream().filter(booking -> booking.getTenant().equals(tenant)).toList();
	}

	public List<Booking> getAllBookings() {
		return housings.stream().flatMap(housing -> housing.getBookings().stream()).toList();
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
}