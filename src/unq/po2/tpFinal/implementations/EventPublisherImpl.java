package unq.po2.tpFinal.implementations;

import java.util.ArrayList;
import java.util.List;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.interfaces.EventPublisher;
import unq.po2.tpFinal.interfaces.PriceDropSubscriber;
import unq.po2.tpFinal.interfaces.ReservationAcceptedSubscriber;
import unq.po2.tpFinal.interfaces.ReservationCancelledSubscriber;

public class EventPublisherImpl implements EventPublisher {
	private List<PriceDropSubscriber> priceDropSubscribers = new ArrayList<>();
	private List<ReservationCancelledSubscriber> reservationCancelledSubscribers = new ArrayList<>();
	private List<ReservationAcceptedSubscriber> reservationAcceptedSubscribers = new ArrayList<>();

	public void subscribeToPriceDrop(PriceDropSubscriber subscriber) {
		priceDropSubscribers.add(subscriber);
	}

	public void unsubscribeFromPriceDrop(PriceDropSubscriber subscriber) {
		priceDropSubscribers.remove(subscriber);
	}

	public void subscribeToReservationCancelled(ReservationCancelledSubscriber subscriber) {
		reservationCancelledSubscribers.add(subscriber);
	}

	public void unsubscribeFromReservationCancelled(ReservationCancelledSubscriber subscriber) {
		reservationCancelledSubscribers.remove(subscriber);
	}

	public void subscribeToReservationAccepted(ReservationAcceptedSubscriber subscriber) {
		reservationAcceptedSubscribers.add(subscriber);
	}

	public void unsubscribeFromReservationAccepted(ReservationAcceptedSubscriber subscriber) {
		reservationAcceptedSubscribers.remove(subscriber);
	}

	public void notifyPriceDrop(String propertyType, double newPrice) {
		String message = "No te pierdas esta oferta: Un inmueble " + propertyType + " a tan sólo " + newPrice
				+ " pesos.";
		for (PriceDropSubscriber subscriber : priceDropSubscribers) {
			subscriber.onPriceDrop(message);
		}
	}

	public void notifyReservationCancelled(String propertyType) {
		String message = "El/la " + propertyType + " que te interesa se ha liberado! Corre a reservarlo!";
		for (ReservationCancelledSubscriber subscriber : reservationCancelledSubscribers) {
			subscriber.onReservationCancelled(message);
		}
	}

	public void notifyReservationAccepted(Booking booking) {
		for (ReservationAcceptedSubscriber subscriber : reservationAcceptedSubscribers) {
			subscriber.onReservationAccepted(booking);
		}
	}
}
