package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import java.util.*;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import unq.po2.tpFinal.interfaces.*;

import org.junit.jupiter.api.*;

public class ConfirmationEmailSenderTest {
	private EmailSender emailSenderMock;
    private ConfirmationEmailSender confirmationEmailSender;

    @BeforeEach
    public void setUp() {
        emailSenderMock = mock(EmailSender.class);
        confirmationEmailSender = new ConfirmationEmailSender(emailSenderMock);
    }

    @Test
    public void testNotifyBookingAcceptedSendsConfirmationEmail() {
        Booking booking = mock(Booking.class);
        Tenant tenant = mock(Tenant.class);
        when(booking.getTenant()).thenReturn(tenant);
        when(tenant.getEmail()).thenReturn("tenant@example.com");
        confirmationEmailSender.notifyBookingAccepted(booking);
        verify(emailSenderMock).sendEmail("tenant@example.com", "Reserva aceptada");
    }
}
