package unq.po2.tpFinal;

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