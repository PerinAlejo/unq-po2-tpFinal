package unq.po2.tpFinal.interfaces;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;

public interface HousingObserver{
	public void notifyBookingAccepted(Housing housing, Booking booking);
	public void notifyBookingCancelled(Housing housing, Booking booking);
	public void notifyPriceDrop(Housing housing, double newPrice);
}