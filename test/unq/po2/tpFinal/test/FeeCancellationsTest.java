package unq.po2.tpFinal.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Set;

import unq.po2.tpFinal.domain.*;
import unq.po2.tpFinal.implementations.*;
import org.junit.jupiter.api.*;

public class FeeCancellationsTest {
	private Housing housingMock;
    private FreeCancellation freeCancellation;

    @BeforeEach
    public void setUp() {
        housingMock = mock(Housing.class);
        freeCancellation = new FreeCancellation(housingMock);
    }

    @Test
    public void testGetCancellationFeeMoreThanTenDaysBefore() {
        DateRange range = new DateRange(LocalDate.now().plusDays(15), LocalDate.now().plusDays(20));

        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(0.0, fee, 0.00);
    }

    @Test
    public void testGetCancellationFeeBetweenOneAndTenDaysBefore() {
        DateRange range = new DateRange(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10));
        DateRange twoDayRange = new DateRange(range.getStart(), range.getStart().plusDays(2));

        when(housingMock.getPrice(twoDayRange)).thenReturn(200.0);

        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(200.0, fee, 0.00);
    }

    @Test
    public void testGetCancellationFeeOnOrAfterStartDate() {
        DateRange range = new DateRange(LocalDate.now().minusDays(1), LocalDate.now().plusDays(5));

        when(housingMock.getPrice(range)).thenReturn(500.0);

        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(500.0, fee, 0.01);
    }
}
