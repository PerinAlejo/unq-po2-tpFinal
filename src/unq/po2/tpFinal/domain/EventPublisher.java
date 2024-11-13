package unq.po2.tpFinal.domain;

public interface EventPublisher {
	void subscribeToPriceDrop(PriceDropSubscriber subscriber);
    void unsubscribeFromPriceDrop(PriceDropSubscriber subscriber);
    void subscribeToReservationCancelled(ReservationCancelledSubscriber subscriber);
    void unsubscribeFromReservationCancelled(ReservationCancelledSubscriber subscriber);
}
