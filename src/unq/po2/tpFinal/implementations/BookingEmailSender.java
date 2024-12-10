package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.interfaces.HousingObserver;
import unq.po2.tpFinal.interfaces.EmailSender;

public class BookingEmailSender implements HousingObserver {
	private EmailSender emailSender;

	public BookingEmailSender(EmailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	@Override
	public void notifyBookingAccepted(Housing housing, Booking booking) {
		this.emailSender.sendEmail(booking.getTenant().getEmail(), "Reserva aceptada");
		
	}

	@Override
	public void notifyBookingCancelled(Housing housing, Booking booking) {
		this.emailSender.sendEmail(booking.getTenant().getEmail(), "Se cancelo la reserva");
	}

	@Override
	public void notifyPriceDrop(Housing housing, double newPrice) {
		
	}
}
