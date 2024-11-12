package unq.po2.tpFinal;

public class BookingNormalStrategy implements BookingAcceptanceStrategy {
	@Override
	public boolean isAcceptable(Booking booking) {
	    return !booking.getHousing().isOccupied(booking.getRange());
	}
}
