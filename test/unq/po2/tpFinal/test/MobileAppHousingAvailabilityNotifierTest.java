package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.implementations.MobileAppHousingAvailabilityNotifier;
import unq.po2.tpFinal.implementations.PopUpWindow;

class MobileAppHousingAvailabilityNotifierTest {

	private MobileAppHousingAvailabilityNotifier notifier;
	private PopUpWindow popUpWindowMock;
	private Housing housingMock;
	private Booking bookingMock;
	private DateRange rangeMock;

	@BeforeEach
	void setUp() {
		popUpWindowMock = mock(PopUpWindow.class);
		housingMock = mock(Housing.class);
		bookingMock = mock(Booking.class);
		rangeMock = mock(DateRange.class);
		when(bookingMock.getRange()).thenReturn(rangeMock);
		notifier = new MobileAppHousingAvailabilityNotifier(popUpWindowMock);
	}

	@Test
	void testNotifyBookingCancelledWhenHousingBecomesAvailable() {
		HousingType housingTypeMock = mock(HousingType.class);
		when(housingTypeMock.getName()).thenReturn("departamento");
		when(housingMock.getHousingType()).thenReturn(housingTypeMock);
		when(housingMock.isAvailable(rangeMock)).thenReturn(true);
		notifier.notifyBookingCancelled(housingMock, bookingMock);

		verify(popUpWindowMock).popUp("El/la departamento que te interesa se ha liberado! Corre a reservarlo!", "green",
				12);
	}

	@Test
	void testNotifyBookingCancelledWhenHousingIsNotAvailable() {
		when(housingMock.isAvailable(bookingMock.getRange())).thenReturn(false);

		notifier.notifyBookingCancelled(housingMock, bookingMock);

		verify(popUpWindowMock, never()).popUp(anyString(), anyString(), anyInt());
	}
}
