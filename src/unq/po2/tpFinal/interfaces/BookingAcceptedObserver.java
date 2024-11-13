package unq.po2.tpFinal.interfaces;

import unq.po2.tpFinal.domain.Booking;

public interface BookingAcceptedObserver{
	public void notifyBookingAccepted(Booking booking);
}