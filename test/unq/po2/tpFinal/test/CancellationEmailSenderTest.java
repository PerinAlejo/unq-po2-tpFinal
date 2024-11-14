package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import java.util.*;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import unq.po2.tpFinal.interfaces.*;

import org.junit.jupiter.api.*;

public class CancellationEmailSenderTest {
	private EmailSender emailSenderMock;
    private CancellationEmailSender cancellationEmailSender;

    @BeforeEach
    public void setUp() {
        emailSenderMock = mock(EmailSender.class);
        cancellationEmailSender = new CancellationEmailSender(emailSenderMock);
    }

    @Test
    public void testNotifyBookingCancelledSendsCancellationEmail() {
        Booking booking = mock(Booking.class);
        Tenant tenant = mock(Tenant.class);
        when(booking.getTenant()).thenReturn(tenant);
        when(tenant.getEmail()).thenReturn("tenant@example.com");
        cancellationEmailSender.notifyBookingCancelled(booking);
        verify(emailSenderMock).sendEmail("tenant@example.com", "Se cancelo la reserva");
    }
}
