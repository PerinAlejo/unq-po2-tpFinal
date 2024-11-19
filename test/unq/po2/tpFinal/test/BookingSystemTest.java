package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.interfaces.HousingObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemTest {

	private BookingSystem bookingSystem;

	private List<HousingObserver> mockAcceptedObservers;
	private Booking mockBooking;
	private Tenant mockTenant;
	private City mockCity;
	private Housing mockHousing;
	private HousingType mockHousingType;

	@BeforeEach
	public void setUp() {

		mockAcceptedObservers = new ArrayList<>();
		mockBooking = mock(Booking.class);
		mockTenant = mock(Tenant.class);
		mockCity = mock(City.class);
		mockHousing = mock(Housing.class);
		mockHousingType = mock(HousingType.class);

		bookingSystem = new BookingSystem();
	}

	@Test
	public void testNotifyBookingAccepted() {
		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(mockBooking));
	}

	@Test
	public void testCancelBooking() {
		when(mockBooking.getHousing()).thenReturn(mockHousing);
		when(mockHousing.getHousingType()).thenReturn(mockHousingType);
		when(mockHousingType.getName()).thenReturn("Apartment");
		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);
		bookingSystem.notifyBookingCancelled(mockHousing, mockBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(0, allBookings.size());
	}

	@Test
	public void testGetAllBookings() {
		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(mockBooking));
	}

	@Test
	public void testGetAllBookingsForTenant() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);

		List<Booking> tenantBookings = bookingSystem.getAllBookings(mockTenant);
		assertEquals(1, tenantBookings.size());
		assertTrue(tenantBookings.contains(mockBooking));
	}

	@Test
	public void testGetFutureBookings() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		when(mockBooking.startsAfter(any(LocalDate.class))).thenReturn(true);

		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);

		List<Booking> futureBookings = bookingSystem.getFutureBookings(mockTenant);
		assertEquals(1, futureBookings.size());
		assertTrue(futureBookings.contains(mockBooking));
	}

	@Test
	public void testGetBookingsFromCity() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		when(mockBooking.isOnCity(mockCity)).thenReturn(true);

		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);

		List<Booking> cityBookings = bookingSystem.getBookingsFromCity(mockTenant, mockCity);
		assertEquals(1, cityBookings.size());
		assertTrue(cityBookings.contains(mockBooking));
	}

	@Test
	public void testGetBookingCities() {
		when(mockBooking.getTenant()).thenReturn(mockTenant);
		when(mockBooking.getCity()).thenReturn(mockCity);

		bookingSystem.notifyBookingAccepted(mockHousing, mockBooking);

		List<City> bookingCities = bookingSystem.getBookingCities(mockTenant);
		assertEquals(1, bookingCities.size());
		assertTrue(bookingCities.contains(mockCity));
	}


	@Test
	public void testCancelBookingProcessesNextConditionalBooking() {
		Booking existingBooking = mock(Booking.class);
		Booking conditionalBooking = mock(Booking.class);
		Housing housing = mock(Housing.class);
		DateRange existingRange = mock(DateRange.class);
		DateRange conditionalRange = mock(DateRange.class);

		when(existingBooking.getHousing()).thenReturn(housing);
		when(existingBooking.getRange()).thenReturn(existingRange);

		when(conditionalBooking.getHousing()).thenReturn(housing);
		when(conditionalBooking.getRange()).thenReturn(conditionalRange);

		when(existingRange.overlaps(conditionalRange)).thenReturn(true);

		when(mockHousing.getHousingType()).thenReturn(mockHousingType);
		when(mockHousingType.getName()).thenReturn("Apartment");
		when(existingBooking.getHousing()).thenReturn(mockHousing);
		when(existingBooking.getRange()).thenReturn(existingRange);
		when(conditionalBooking.getHousing()).thenReturn(mockHousing);
		when(conditionalBooking.getRange()).thenReturn(conditionalRange);

		bookingSystem.notifyBookingAccepted(mockHousing, existingBooking);
		bookingSystem.notifyBookingAccepted(mockHousing, conditionalBooking);

		bookingSystem.notifyBookingCancelled(mockHousing, existingBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(conditionalBooking));
	}

}