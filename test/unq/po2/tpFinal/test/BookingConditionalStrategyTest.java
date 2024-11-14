package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import java.util.*;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import org.junit.jupiter.api.*;

public class BookingConditionalStrategyTest {
	private Map<Housing, Queue<Booking>> conditionalBookingsMock;
    private BookingConditionalStrategy bookingConditionalStrategy;

    @BeforeEach
    public void setUp() {
        conditionalBookingsMock = mock(Map.class);
        bookingConditionalStrategy = new BookingConditionalStrategy(conditionalBookingsMock);
    }

    @Test
    public void testProcessAddBookingToConditionalBookings() {
        Housing housing = mock(Housing.class);
        Booking booking = mock(Booking.class);

        when(booking.getHousing()).thenReturn(housing);

        Queue<Booking> queueMock = mock(Queue.class);
        when(conditionalBookingsMock.computeIfAbsent(eq(housing), any())).thenReturn(queueMock);

        bookingConditionalStrategy.process(booking);

        verify(queueMock).add(booking);
        verify(conditionalBookingsMock).computeIfAbsent(eq(housing), any());
    }
}
