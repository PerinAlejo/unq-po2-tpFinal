package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import java.util.Set;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.implementations.*;
import org.junit.jupiter.api.*;

public class BookingConfirmedStrategyTest {
	private Set<Booking> confirmedBookingsMock;
    private BookingConfirmedStrategy bookingConfirmedStrategy;

    @BeforeEach
    public void setUp() {
        confirmedBookingsMock = mock(Set.class);
        bookingConfirmedStrategy = new BookingConfirmedStrategy(confirmedBookingsMock);
    }

    @Test
    public void testProcessAddBookingToConfirmedBookings() {
        Booking booking = mock(Booking.class);
        bookingConfirmedStrategy.process(booking);
        verify(confirmedBookingsMock).add(booking);
    }
}
