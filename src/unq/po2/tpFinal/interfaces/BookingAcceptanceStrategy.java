package unq.po2.tpFinal.interfaces;

import unq.po2.tpFinal.domain.Booking;

public interface BookingAcceptanceStrategy{
	public boolean isAcceptable(Booking booking); 
}