package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import unq.po2.tpFinal.domain.Address;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.CancellationPolicy;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingStayDetails;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.domain.Picture;
import unq.po2.tpFinal.domain.PriceForRange;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.interfaces.HousingObserver;
import unq.po2.tpFinal.interfaces.PaymentMethod;
import unq.po2.tpFinal.interfaces.PriceCalculatorInterface;
import unq.po2.tpFinal.interfaces.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HousingTest {

	private Housing housing;
	private HousingType mockHousingType;
	private Address mockAddress;
	private List<Service> mockServices;
	private List<Picture> mockPictures;
	private HousingStayDetails mockStayDetails;
	private List<PaymentMethod> mockPaymentMethods;
	private PriceCalculatorInterface mockPriceCalculator;
	private CancellationPolicy mockCancellationPolicy;
	private Owner mockOwner;
	private PaymentMethod mockDefaultPaymentMethod;

	@BeforeEach
	public void setUp() {
		// Inicializar mocks
		mockHousingType = mock(HousingType.class);
		mockAddress = mock(Address.class);
		mockServices = new ArrayList<>();
		mockPictures = new ArrayList<>();
		mockStayDetails = mock(HousingStayDetails.class);
		mockDefaultPaymentMethod = mock(PaymentMethod.class);
		mockPaymentMethods = Arrays.asList(mockDefaultPaymentMethod);
		mockPriceCalculator = mock(PriceCalculatorInterface.class);
		mockOwner = mock(Owner.class);
		mockCancellationPolicy = mock(CancellationPolicy.class);

		housing = new Housing(mockHousingType, 100.0f, mockAddress, mockServices, 4, mockPictures, mockStayDetails,
				mockPaymentMethods, mockPriceCalculator, mockOwner, mockCancellationPolicy);
	}

	@Test
	public void testGetPrice() {
		DateRange mockDateRange = mock(DateRange.class);
		double expectedPrice = 200.0;

		when(mockPriceCalculator.getPrice(mockDateRange)).thenReturn(expectedPrice);

		double price = housing.getPrice(mockDateRange);

		assertEquals(expectedPrice, price);
		verify(mockPriceCalculator).getPrice(mockDateRange);
	}

	@Test
	public void testGetCapacity() {
		int capacity = housing.getCapacity();
		assertEquals(4, capacity);
	}

	@Test
	public void testGetAddress() {
		Address address = housing.getAddress();
		assertEquals(mockAddress, address);
	}

	@Test
	public void testIsLocatedIn() {
		City mockCity = mock(City.class);
		when(mockAddress.getCity()).thenReturn(mockCity);

		assertTrue(housing.isLocatedIn(mockCity));
	}

	@Test
	public void testIsAvailable() {
		DateRange mockDateRange = mock(DateRange.class);
		assertTrue(housing.isAvailable(mockDateRange)); // Asumiendo que siempre devuelve true
	}

	@Test
	public void testGetOwner() {
		Owner owner = housing.getOwner();
		assertEquals(mockOwner, owner);
	}

	@Test
	public void testAddRanking() {
		Ranking mockRanking = mock(Ranking.class);
		housing.addRanking(mockRanking);

		List<Ranking> rankings = housing.getRankings();
		assertEquals(1, rankings.size());
		assertTrue(rankings.contains(mockRanking));
	}

	@Test
	public void testGetCity() {
		City mockCity = mock(City.class);
		when(mockAddress.getCity()).thenReturn(mockCity);

		City city = housing.getCity();
		assertEquals(mockCity, city);
	}

	@Test
	public void testGetCancelationFee() {
		DateRange mockRange = mock(DateRange.class);
		when(mockCancellationPolicy.getCancellationFee(mockRange)).thenReturn(1.0);
		double fee = housing.getCancelationFee(mockRange);
		assertEquals(1.0, fee, 0.0);
	}

	@Test
	public void testGetPaymentMethod() {
		assertEquals(this.mockDefaultPaymentMethod, housing.getDefaultPaymentMethod());
	}

	@Test
	public void availabilityChangesAfterBeingBooked() {
		Booking mockBooking = mock(Booking.class);
		DateRange range = mock(DateRange.class);
		when(mockBooking.isBookedOnRange(range)).thenReturn(true);
		assertEquals(true, housing.isAvailable(range));
		housing.book(mockBooking);
		assertEquals(false, housing.isAvailable(range));
	}

	@Test
	public void cancelsBook() {
		Booking mockBooking = mock(Booking.class);
		DateRange range = mock(DateRange.class);
		when(mockBooking.isBookedOnRange(range)).thenReturn(true);
		housing.book(mockBooking);
		housing.cancelBook(mockBooking);
		assertEquals(true, housing.isAvailable(range));
	}
	
	@Test
	public void notifiesObserversOnPriceDrop() {
		HousingObserver observerMock = mock(HousingObserver.class);
		housing.addObserver(observerMock);
		PriceForRange priceForRange = mock(PriceForRange.class);
		housing.priceDropped(priceForRange);
		verify(observerMock).notifyPriceDrop(housing, 0.0);
	}
	
	@Test
	public void notifiesObserverOnBooked() {
		HousingObserver observerMock = mock(HousingObserver.class);
		housing.addObserver(observerMock);
		Booking mockBooking = mock(Booking.class);
		housing.markAsBooked(mockBooking);
		verify(observerMock).notifyBookingAccepted(housing, mockBooking);
	}
	
	@Test
	public void notifiesObserverOnBookingCancelled() {
		HousingObserver observerMock = mock(HousingObserver.class);
		housing.addObserver(observerMock);
		Booking mockBooking = mock(Booking.class);
		housing.bookingIsCancelled(mockBooking);
		verify(observerMock).notifyBookingCancelled(housing, mockBooking);
	}
	
	@Test
	public void removesObserverAndDoesNotNotify() {
		HousingObserver observerMock = mock(HousingObserver.class);
		housing.addObserver(observerMock);
		housing.removeObserver(observerMock);
		Booking mockBooking = mock(Booking.class);
		housing.bookingIsCancelled(mockBooking);
		verify(observerMock, never()).notifyBookingCancelled(housing, mockBooking);
	}
	
	@Test
	public void returnType() {
		assertNotNull(housing.getHousingType());
	}
}