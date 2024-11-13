package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.views.OwnerRentalView;

public class OwnerRentalViewTest {

	@Mock
	private Owner mockOwner;

	@Mock
	private Housing mockHousing;

	@InjectMocks
	private OwnerRentalView ownerRentalView;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		ownerRentalView = new OwnerRentalView(mockOwner, mockHousing);
	}

	@Test
	public void testGetTotalRentsForHousing() {

		Booking booking1 = mock(Booking.class);
		Booking booking2 = mock(Booking.class);

		when(booking1.getHousing()).thenReturn(mockHousing);
		when(booking2.getHousing()).thenReturn(mockHousing);

		Housing otherHousing = mock(Housing.class);
		Booking booking3 = mock(Booking.class);
		when(booking3.getHousing()).thenReturn(otherHousing);

		when(mockOwner.getRentals()).thenReturn(Arrays.asList(booking1, booking2, booking3));

		long totalRentsForHousing = ownerRentalView.getTotalRentsForHousing();

		assertEquals(2, totalRentsForHousing);
	}

	@Test
	public void testGetTotalRentsForAllHousings() {
		Booking booking1 = mock(Booking.class);
		Booking booking2 = mock(Booking.class);
		Booking booking3 = mock(Booking.class);

		when(mockOwner.getRentals()).thenReturn(Arrays.asList(booking1, booking2, booking3));

		int totalRentsForAllHousings = ownerRentalView.getTotalRentsForAllHousings();

		assertEquals(3, totalRentsForAllHousings);
	}

	@Test
	public void testGetTotalTimesHasRentedCurrentHousing() {

		Booking booking1 = mock(Booking.class);
		Booking booking2 = mock(Booking.class);

		when(booking1.getHousing()).thenReturn(mockHousing);
		when(booking2.getHousing()).thenReturn(mockHousing);

		when(mockOwner.getRentals()).thenReturn(Arrays.asList(booking1, booking2));

		long totalTimesRentedCurrentHousing = ownerRentalView.getTotalTimesHasRentedCurrentHousing();

		assertEquals(2, totalTimesRentedCurrentHousing);
	}

	@Test
	public void testGetRentalsForCurrentHousing() {

		Booking booking1 = mock(Booking.class);
		Booking booking2 = mock(Booking.class);

		when(booking1.getHousing()).thenReturn(mockHousing);
		when(booking2.getHousing()).thenReturn(mockHousing);

		Housing otherHousing = mock(Housing.class);
		Booking booking3 = mock(Booking.class);
		when(booking3.getHousing()).thenReturn(otherHousing);

		when(mockOwner.getRentals()).thenReturn(Arrays.asList(booking1, booking2, booking3));

		List<Housing> rentalsForCurrentHousing = ownerRentalView.getRentalsForCurrentHousing();

		assertEquals(Arrays.asList(mockHousing, mockHousing), rentalsForCurrentHousing);
	}
}
