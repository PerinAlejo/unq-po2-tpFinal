package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.interfaces.BookingAcceptedObserver;
import unq.po2.tpFinal.interfaces.BookingCancelledObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemTest {

	private BookingSystem bookingSystem;
	private List<BookingCancelledObserver> mockCancelledObservers;
	private List<BookingAcceptedObserver> mockAcceptedObservers;
	private Booking mockBooking;
	private Tenant mockTenant;
	private City mockCity;

	@BeforeEach
	public void setUp() {
		mockCancelledObservers = new ArrayList<>();
		mockAcceptedObservers = new ArrayList<>();
		mockBooking = mock(Booking.class);
		mockTenant = mock(Tenant.class);
		mockCity = mock(City.class);

		bookingSystem = new BookingSystem(mockCancelledObservers, mockAcceptedObservers);
	}

	@Test
	public void testNotifyBookingAccepted() {
		bookingSystem.notifyBookingAccepted(mockBooking);

		// Verificar que el booking fue agregado
		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(mockBooking));
	}

	@Test
	public void testCancelBooking() {
		bookingSystem.notifyBookingAccepted(mockBooking);

		// Cancelar la reserva
		bookingSystem.cancelBooking(mockBooking);

		// Verificar que el booking fue removido
		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(0, allBookings.size());
	}

	@Test
	public void testCancelBookingNotifiesObservers() {
		BookingCancelledObserver mockObserver = mock(BookingCancelledObserver.class);
		mockCancelledObservers.add(mockObserver);

		bookingSystem.notifyBookingAccepted(mockBooking);

		// Cancelar la reserva
		bookingSystem.cancelBooking(mockBooking);

		// Verificar que el observer fue notificado
		verify(mockObserver).notifyBookingCancelled(mockBooking);
	}

	@Test
	public void testGetAllBookings() {
		bookingSystem.notifyBookingAccepted(mockBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(mockBooking));
	}

	@Test
	public void testGetAllBookingsForTenant() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		bookingSystem.notifyBookingAccepted(mockBooking);

		List<Booking> tenantBookings = bookingSystem.getAllBookings(mockTenant);
		assertEquals(1, tenantBookings.size());
		assertTrue(tenantBookings.contains(mockBooking));
	}

	@Test
	public void testGetFutureBookings() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		when(mockBooking.startsAfter(any(LocalDate.class))).thenReturn(true);

		bookingSystem.notifyBookingAccepted(mockBooking);

		List<Booking> futureBookings = bookingSystem.getFutureBookings(mockTenant);
		assertEquals(1, futureBookings.size());
		assertTrue(futureBookings.contains(mockBooking));
	}

	@Test
	public void testGetBookingsFromCity() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		when(mockBooking.isOnCity(mockCity)).thenReturn(true);

		bookingSystem.notifyBookingAccepted(mockBooking);

		List<Booking> cityBookings = bookingSystem.getBookingsFromCity(mockTenant, mockCity);
		assertEquals(1, cityBookings.size());
		assertTrue(cityBookings.contains(mockBooking));
	}

	@Test
	public void testGetBookingCities() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		when(mockBooking.getCity()).thenReturn(mockCity);

		bookingSystem.notifyBookingAccepted(mockBooking);

		List<City> bookingCities = bookingSystem.getBookingCities(mockTenant);
		assertEquals(1, bookingCities.size());
		assertTrue(bookingCities.contains(mockCity));
	}
}