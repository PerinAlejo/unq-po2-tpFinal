package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.interfaces.HousingObserver;

public class MobileAppHousingAvailabilityNotifier implements HousingObserver {

	private PopUpWindow popUpWindow;
	
	public MobileAppHousingAvailabilityNotifier(PopUpWindow popUpWindow) {
		this.popUpWindow = popUpWindow;
	}

	@Override
	public void notifyBookingAccepted(Housing housing, Booking booking) { }

	@Override
	public void notifyBookingCancelled(Housing housing, Booking booking) {
		if (housing.isAvailable(booking.getRange())) {
			this.popUpWindow.popUp(
					"El/la " + housing.getHousingType().getName() + " que te interesa se ha liberado! Corre a reservarlo!", "green",
					12);
		}
	}

	@Override
	public void notifyPriceDrop(Housing housing, double newPrice) {	}
}
