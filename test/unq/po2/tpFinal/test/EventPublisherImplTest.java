package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.implementations.EventPublisherImpl;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.interfaces.PriceDropSubscriber;
import unq.po2.tpFinal.interfaces.ReservationAcceptedSubscriber;
import unq.po2.tpFinal.interfaces.ReservationCancelledSubscriber;

public class EventPublisherImplTest {

    private EventPublisherImpl eventPublisher;
    private PriceDropSubscriber mockPriceDropSubscriber;
    private ReservationCancelledSubscriber mockReservationCancelledSubscriber;
    private ReservationAcceptedSubscriber mockReservationAcceptedSubscriber;
    private Booking mockBooking;

    @BeforeEach
    public void setUp() {
        eventPublisher = new EventPublisherImpl();
        mockPriceDropSubscriber = mock(PriceDropSubscriber.class);
        mockReservationCancelledSubscriber = mock(ReservationCancelledSubscriber.class);
        mockReservationAcceptedSubscriber = mock(ReservationAcceptedSubscriber.class);
        mockBooking = mock(Booking.class);
    }

    @Test
    public void testSubscribeToPriceDrop() {
        eventPublisher.subscribeToPriceDrop(mockPriceDropSubscriber);
        eventPublisher.notifyPriceDrop("House", 5000);
        verify(mockPriceDropSubscriber).onPriceDrop(anyString());
    }

    @Test
    public void testUnsubscribeFromPriceDrop() {
        eventPublisher.subscribeToPriceDrop(mockPriceDropSubscriber);
        eventPublisher.unsubscribeFromPriceDrop(mockPriceDropSubscriber);
        eventPublisher.notifyPriceDrop("House", 5000);
        verify(mockPriceDropSubscriber, never()).onPriceDrop(anyString());
    }

    @Test
    public void testSubscribeToReservationCancelled() {
        eventPublisher.subscribeToReservationCancelled(mockReservationCancelledSubscriber);
        eventPublisher.notifyReservationCancelled("House");
        verify(mockReservationCancelledSubscriber).onReservationCancelled(anyString());
    }

    @Test
    public void testUnsubscribeFromReservationCancelled() {
        eventPublisher.subscribeToReservationCancelled(mockReservationCancelledSubscriber);
        eventPublisher.unsubscribeFromReservationCancelled(mockReservationCancelledSubscriber);
        eventPublisher.notifyReservationCancelled("House");
        verify(mockReservationCancelledSubscriber, never()).onReservationCancelled(anyString());
    }

    @Test
    public void testSubscribeToReservationAccepted() {
        eventPublisher.subscribeToReservationAccepted(mockReservationAcceptedSubscriber);
        eventPublisher.notifyReservationAccepted(mockBooking);
        verify(mockReservationAcceptedSubscriber).onReservationAccepted(mockBooking);
    }

    @Test
    public void testUnsubscribeFromReservationAccepted() {
        eventPublisher.subscribeToReservationAccepted(mockReservationAcceptedSubscriber);
        eventPublisher.unsubscribeFromReservationAccepted(mockReservationAcceptedSubscriber);
        eventPublisher.notifyReservationAccepted(mockBooking);
        verify(mockReservationAcceptedSubscriber, never()).onReservationAccepted(mockBooking);
    }
}
