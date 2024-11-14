package unq.po2.tpFinal.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Set;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import org.junit.jupiter.api.*;


public class NoCancellationTest {
	private Housing housingMock;
    private NoCancellation noCancellation;

    @BeforeEach
    public void setUp() {
        housingMock = mock(Housing.class);
        noCancellation = new NoCancellation(housingMock);
    }

    @Test
    public void testGetCancellationFee() {
        DateRange range = new DateRange(LocalDate.now().plusDays(10), LocalDate.now().plusDays(15));
        when(housingMock.getPrice(range)).thenReturn(300.0);
        double fee = noCancellation.getCancellationFee(range);
        assertEquals(300.0, fee, 0.01);
    }
}
