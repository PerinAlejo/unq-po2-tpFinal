package unq.po2.tpFinal.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Set;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import org.junit.jupiter.api.*;


public class IntermediateCancellationTest {
	private Housing housingMock;
    private IntermediateCancellation intermediateCancellation;

    @BeforeEach
    public void setUp() {
        housingMock = mock(Housing.class);
        intermediateCancellation = new IntermediateCancellation(housingMock);
    }

    @Test
    public void testGetCancellationFeeMoreThanTwentyDaysBefore() {
        DateRange range = new DateRange(LocalDate.now().minusDays(25), 5);
        when(housingMock.getPrice(range)).thenReturn(400.0);
        double fee = intermediateCancellation.getCancellationFee(range);
        assertEquals(0.0, fee, 0.01);
    }

    @Test
    public void testGetCancellationFeeBetweenTenAndTwentyDaysBefore() {
        DateRange range = new DateRange(LocalDate.now().minusDays(14), 14);
        
        when(housingMock.getPrice(range)).thenReturn(400.0);

        double fee = intermediateCancellation.getCancellationFee(range);
        assertEquals(200.0, fee, 0.01);
    }

    @Test
    public void testGetCancellationFeeLessThanTenDaysBefore() {
        DateRange range = new DateRange(LocalDate.now(), 5);

        when(housingMock.getPrice(range)).thenReturn(500.0);

        double fee = intermediateCancellation.getCancellationFee(range);
        assertEquals(500.0, fee, 0.01);
    }
}
