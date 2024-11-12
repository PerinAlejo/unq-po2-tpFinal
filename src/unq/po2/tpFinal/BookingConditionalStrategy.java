package unq.po2.tpFinal;

public class BookingConditionalStrategy implements BookingAcceptanceStrategy{
	public boolean isAcceptable(Booking booking) {
        return booking.getHousing().isOccupied(booking.getRange());
    }
}
