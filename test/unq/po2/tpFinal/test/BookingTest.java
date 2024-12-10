package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.interfaces.HousingObserver;
import unq.po2.tpFinal.interfaces.PaymentMethod;
import unq.po2.tpFinal.interfaces.Ranker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingTest {

	private Booking booking;
	private Housing mockHousing;
	private Tenant mockTenant;
	private DateRange mockDateRange;
	private PaymentMethod mockPaymentMethod;
	private City mockCity;
	private Owner mockOwner;
	private HousingObserver mockObserver;

	@BeforeEach
	public void setUp() {
		mockHousing = mock(Housing.class);
		mockTenant = mock(Tenant.class);
		mockDateRange = mock(DateRange.class);
		mockPaymentMethod = mock(PaymentMethod.class);
		mockCity = mock(City.class);
		mockOwner = mock(Owner.class);
		mockObserver = mock(HousingObserver.class);

		when(mockHousing.getOwner()).thenReturn(mockOwner);
		when(mockHousing.getCity()).thenReturn(mockCity);

		booking = new Booking(mockHousing, mockTenant, mockDateRange, mockPaymentMethod);
	}

	@Test
	public void testGetTenant() {
		assertEquals(mockTenant, booking.getTenant());
	}

	@Test
	public void testGetHousing() {
		assertEquals(mockHousing, booking.getHousing());
	}

	@Test
	public void testGetOwner() {
		Owner owner = booking.getOwner();
		assertNotNull(owner);
		verify(mockHousing).getOwner();
	}

	@Test
	public void testCheckOut() {
		List<Ranking> rankings = new ArrayList<>();
		Ranking mockRanking = mock(Ranking.class);
		rankings.add(mockRanking);

		when(mockRanking.getRanker()).thenReturn(mock(Ranker.class));

		booking.checkOut(rankings);
		verify(mockRanking.getRanker()).rank(mockRanking);
	}

	@Test
	public void testStartsAfter() {
		LocalDate date = LocalDate.now();
		when(mockDateRange.startsAfter(date)).thenReturn(true);

		assertTrue(booking.startsAfter(date));
		verify(mockDateRange).startsAfter(date);
	}

	@Test
	public void testIsOnCity() {
		when(mockHousing.isLocatedIn(mockCity)).thenReturn(true);

		assertTrue(booking.isOnCity(mockCity));
		verify(mockHousing).isLocatedIn(mockCity);
	}

	@Test
	public void testGetCity() {
		assertEquals(mockCity, booking.getCity());
		verify(mockHousing).getCity();
	}

	@Test
	public void testIsBookedOn() {
		LocalDate date = LocalDate.now();
		when(mockDateRange.contains(date)).thenReturn(true);

		assertTrue(booking.isBookedOn(date));
		verify(mockDateRange).contains(date);
	}

	@Test
	public void testGetRange() {
		assertEquals(mockDateRange, booking.getRange());
	}

	@Test
	public void testCancelBook() {
		double cancellationFee = 100.0;
		when(mockHousing.getCancelationFee(mockDateRange)).thenReturn(cancellationFee);
		PaymentMethod defaultPaymentMethod = mock(PaymentMethod.class);
		when(mockHousing.getDefaultPaymentMethod()).thenReturn(defaultPaymentMethod);

		booking.cancelBook();

		verify(mockPaymentMethod).applyCharge(cancellationFee);
		verify(defaultPaymentMethod).applyCharge(cancellationFee);

		verify(mockOwner).cancelBook(booking);
	}
	
	@Test
	public void testIsBookedOnRange() {
		DateRange anotherDateRange = mock(DateRange.class);
		LocalDate start = LocalDate.now();
		LocalDate end = start.plusDays(1);
		when(anotherDateRange.getStart()).thenReturn(start);
		when(anotherDateRange.getEnd()).thenReturn(end);
		when(mockDateRange.contains(start)).thenReturn(false);
		when(mockDateRange.contains(end)).thenReturn(true);

		assertTrue(booking.isBookedOnRange(anotherDateRange));
		verify(anotherDateRange).getStart();
		verify(anotherDateRange).getEnd();
		verify(mockDateRange).contains(start);
		verify(mockDateRange).contains(end);
	}
}
