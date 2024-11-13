package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.interfaces.BookingCancelledObserver;
import unq.po2.tpFinal.interfaces.EmailSender;

public class CancellationEmailSender implements BookingCancelledObserver {
	private EmailSender emailSender;

	public CancellationEmailSender(EmailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	@Override
	public void notifyBookingCancelled(Booking booking) {
		this.emailSender.sendEmail(booking.getTenant().getEmail(), "Se cancelo la reserva");
	}
}