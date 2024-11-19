package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import java.util.*;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import unq.po2.tpFinal.interfaces.*;

import org.junit.jupiter.api.*;
import org.mockito.Mock;

public class BookingEmailSenderTest {
	private EmailSender emailSenderMock;
	private Housing housingMock;
	private BookingEmailSender bookingEmailSender;

	@BeforeEach
	public void setUp() {
		emailSenderMock = mock(EmailSender.class);
		housingMock = mock(Housing.class);
		bookingEmailSender = new BookingEmailSender(emailSenderMock);
	}

	@Test
	public void testNotifyBookingCancelledSendsCancellationEmail() {
		Booking booking = mock(Booking.class);
		Tenant tenant = mock(Tenant.class);
		when(booking.getTenant()).thenReturn(tenant);
		when(tenant.getEmail()).thenReturn("tenant@example.com");
		bookingEmailSender.notifyBookingCancelled(housingMock, booking);
		verify(emailSenderMock).sendEmail("tenant@example.com", "Se cancelo la reserva");
	}

	@Test
	public void testNotifyBookingAcceptedSendsConfirmationEmail() {
		Booking booking = mock(Booking.class);
		Tenant tenant = mock(Tenant.class);
		when(booking.getTenant()).thenReturn(tenant);
		when(tenant.getEmail()).thenReturn("tenant@example.com");
		bookingEmailSender.notifyBookingAccepted(housingMock, booking);
		verify(emailSenderMock).sendEmail("tenant@example.com", "Reserva aceptada");
	}
}
