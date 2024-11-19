package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.BookingStatus;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;

class BookingStatusTest {

    @Mock
    private Booking bookingMock1;
    
    @Mock
    private Booking bookingMock2;
    
    @Mock
    private DateRange dateRangeMock;

    @Mock
    private Housing housingMock;
    
    @InjectMocks
    private BookingStatus bookingStatus;    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingStatus = new BookingStatus();
    }

    @Test
    void testIsAvailableWhenNoBookingsOverlap() {
        when(bookingMock1.isBookedOnRange(dateRangeMock)).thenReturn(false);
        bookingStatus.book(bookingMock1, housingMock);
        assertTrue(bookingStatus.isAvailable(dateRangeMock));
    }

    @Test
    void testIsAvailableWhenBookingsOverlap() {
        when(bookingMock1.isBookedOnRange(dateRangeMock)).thenReturn(true);
        bookingStatus.book(bookingMock1, housingMock);
        assertFalse(bookingStatus.isAvailable(dateRangeMock));
    }

    @Test
    void testBookWhenAvailable() {
        when(bookingMock1.getRange()).thenReturn(dateRangeMock);
        when(bookingMock1.isBookedOnRange(dateRangeMock)).thenReturn(false);

        bookingStatus.book(bookingMock1, housingMock);

        assertTrue(bookingStatus.isAvailable(dateRangeMock));
        verify(housingMock).markAsBooked(bookingMock1);
    }

    @Test
    void testBookWhenUnavailableAddsToWaitlist() {
        when(bookingMock1.getRange()).thenReturn(dateRangeMock);
        when(bookingMock1.isBookedOnRange(dateRangeMock)).thenReturn(true);
        when(bookingMock2.getRange()).thenReturn(dateRangeMock);
        when(bookingMock2.isBookedOnRange(dateRangeMock)).thenReturn(false);

        bookingStatus.book(bookingMock1, housingMock);
        bookingStatus.book(bookingMock2, housingMock);

        assertFalse(bookingStatus.isAvailable(dateRangeMock));
    }

    @Test
    void testCancelBookingMovesWaitlistedToBookings() {
        when(bookingMock1.getRange()).thenReturn(dateRangeMock);
        when(bookingMock2.getRange()).thenReturn(dateRangeMock);
        when(bookingMock1.isBookedOnRange(dateRangeMock)).thenReturn(true);
        when(bookingMock2.isBookedOnRange(dateRangeMock)).thenReturn(true);

        bookingStatus.book(bookingMock1, housingMock);
        bookingStatus.book(bookingMock2, housingMock);
        bookingStatus.cancelBooking(bookingMock1, housingMock);

        assertFalse(bookingStatus.isAvailable(dateRangeMock));
        verify(housingMock).markAsBooked(bookingMock1);
    }

    @Test
    void testCancelBookingWithNoWaitlist() {
        when(bookingMock1.getRange()).thenReturn(dateRangeMock);
        when(bookingMock1.isBookedOnRange(dateRangeMock)).thenReturn(false);

        bookingStatus.book(bookingMock1, housingMock);
        bookingStatus.cancelBooking(bookingMock1, housingMock);

        assertTrue(bookingStatus.isAvailable(dateRangeMock));
    }
}
