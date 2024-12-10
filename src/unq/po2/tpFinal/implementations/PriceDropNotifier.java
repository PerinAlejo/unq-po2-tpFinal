package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.interfaces.HomePagePublisher;
import unq.po2.tpFinal.interfaces.HousingObserver;

public class PriceDropNotifier implements HousingObserver {

	private HomePagePublisher publisher;

	public PriceDropNotifier(HomePagePublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void notifyBookingAccepted(Housing housing, Booking booking) {

	}

	@Override
	public void notifyBookingCancelled(Housing housing, Booking booking) {

	}

	@Override
	public void notifyPriceDrop(Housing housing, double newPrice) {
		this.publisher.publish("No te pierdas esta oferta: Un inmueble " + housing.getHousingType().getName() + " a tan sólo "
				+ newPrice + " pesos.");
	}
}
