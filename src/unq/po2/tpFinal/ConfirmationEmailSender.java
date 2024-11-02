package unq.po2.tpFinal;

public class ConfirmationEmailSender implements BookingAcceptedObserver{
	private EmailSender emailSender;
	
	public ConfirmationEmailSender(EmailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	@Override
	public void notifyBookingAccepted(Booking booking) {		
		this.emailSender.sendEmail(booking.getTenant().getEmail(), "Reserva aceptada");	
	}
}
