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
import unq.po2.tpFinal.implementations.EventPublisherImpl;
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
	private Housing mockHousing;
    private HousingType mockHousingType;
	private EventPublisherImpl mockEventPublisher;

	@BeforeEach
	public void setUp() {
		mockCancelledObservers = new ArrayList<>();
		mockAcceptedObservers = new ArrayList<>();
		mockBooking = mock(Booking.class);
		mockTenant = mock(Tenant.class);
		mockCity = mock(City.class);
		mockHousing = mock(Housing.class);
		mockHousingType = mock(HousingType.class);
		mockEventPublisher = mock(EventPublisherImpl.class);

		bookingSystem = new BookingSystem(mockCancelledObservers, mockAcceptedObservers, mockEventPublisher);
	}

	@Test
	public void testNotifyBookingAccepted() {
		bookingSystem.notifyBookingAccepted(mockBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(mockBooking));
	}

	@Test
	public void testCancelBooking() {
		when(mockBooking.getHousing()).thenReturn(mockHousing);
	    when(mockHousing.getHousingType()).thenReturn(mockHousingType);
	    when(mockHousingType.getName()).thenReturn("Apartment");
		bookingSystem.notifyBookingAccepted(mockBooking);
		bookingSystem.cancelBooking(mockBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(0, allBookings.size());
	}

	@Test
	public void testCancelBookingNotifiesObservers() {
		BookingCancelledObserver mockObserver = mock(BookingCancelledObserver.class);
		mockCancelledObservers.add(mockObserver);
		when(mockBooking.getHousing()).thenReturn(mockHousing);
	    when(mockHousing.getHousingType()).thenReturn(mockHousingType);
	    when(mockHousingType.getName()).thenReturn("Apartment");
		bookingSystem.notifyBookingAccepted(mockBooking);

		bookingSystem.cancelBooking(mockBooking);

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

	@Test
	public void testProcessBookingWhenHousingNotAvailable() {
		Booking existingBooking = mock(Booking.class);
		Booking newBooking = mock(Booking.class);
		Housing housing = mock(Housing.class);
		DateRange existingRange = mock(DateRange.class);
		DateRange newRange = mock(DateRange.class);

		when(existingBooking.getHousing()).thenReturn(housing);
		when(existingBooking.getRange()).thenReturn(existingRange);

		when(newBooking.getHousing()).thenReturn(housing);
		when(newBooking.getRange()).thenReturn(newRange);

		when(existingRange.overlaps(newRange)).thenReturn(true);

		bookingSystem.notifyBookingAccepted(existingBooking);
		bookingSystem.notifyBookingAccepted(newBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(existingBooking));
		assertFalse(allBookings.contains(newBooking));
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

		bookingSystem.notifyBookingAccepted(existingBooking);
		bookingSystem.notifyBookingAccepted(conditionalBooking);

		bookingSystem.cancelBooking(existingBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(conditionalBooking));
	}

	@Test
	public void testIsHousingAvailableOverlaps() {
		Booking existingBooking = mock(Booking.class);
		Booking newBooking = mock(Booking.class);
		Housing housing = mock(Housing.class);
		DateRange existingRange = mock(DateRange.class);
		DateRange newRange = mock(DateRange.class);

		when(existingBooking.getHousing()).thenReturn(housing);
		when(existingBooking.getRange()).thenReturn(existingRange);

		when(newBooking.getHousing()).thenReturn(housing);
		when(newBooking.getRange()).thenReturn(newRange);

		when(existingRange.overlaps(newRange)).thenReturn(true);

		bookingSystem.notifyBookingAccepted(existingBooking);

		bookingSystem.notifyBookingAccepted(newBooking);

		List<Booking> allBookings = bookingSystem.getAllBookings();
		assertEquals(1, allBookings.size());
		assertTrue(allBookings.contains(existingBooking));
		assertFalse(allBookings.contains(newBooking));
	}

	@Test
	public void testNotifyObserversBookingAccepted() {
	    BookingAcceptedObserver observer = mock(BookingAcceptedObserver.class);
	    bookingSystem.bookingAcceptedObservers.add(observer);

	    Booking existingBooking = mock(Booking.class);
	    Booking waitlistedBooking = mock(Booking.class);
	    Housing housing = mock(Housing.class);
	    HousingType housingType = mock(HousingType.class);
	    DateRange existingRange = mock(DateRange.class);
	    DateRange waitlistedRange = mock(DateRange.class);
	    
	    when(housing.getHousingType()).thenReturn(housingType);
	    when(housingType.getName()).thenReturn("Apartment");

	    when(existingBooking.getHousing()).thenReturn(housing);
	    when(existingBooking.getRange()).thenReturn(existingRange);

	    when(waitlistedBooking.getHousing()).thenReturn(housing);
	    when(waitlistedBooking.getRange()).thenReturn(waitlistedRange);

	    when(existingRange.overlaps(waitlistedRange)).thenReturn(true);

	    bookingSystem.notifyBookingAccepted(existingBooking);

	    bookingSystem.notifyBookingAccepted(waitlistedBooking);

	    bookingSystem.cancelBooking(existingBooking);

	    verify(observer).notifyBookingAccepted(waitlistedBooking);
	}

}