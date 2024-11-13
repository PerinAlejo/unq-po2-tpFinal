package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.implementations.FreeCancellation;

public class FreeCancellationsTest {

    private Housing housingMock;
    private FreeCancellation freeCancellation;

    @BeforeEach
    public void setUp() {
        housingMock = mock(Housing.class);
        freeCancellation = new FreeCancellation(housingMock);
    }

    @Test
    public void testGetCancellationFeeFreeCancellationPeriod() {
        DateRange range = new DateRange(LocalDate.now().plusDays(15), LocalDate.now().plusDays(20));
        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(0.0, fee, 0.01);
    }

    @Test
    public void testGetCancellationFeeWithinChargeablePeriod() {
        DateRange range = new DateRange(LocalDate.now().plusDays(5), LocalDate.now().plusDays(10));
        DateRange twoDayRange = new DateRange(range.getStart(), range.getStart().plusDays(2));

        when(housingMock.getPrice(argThat(matchesDateRange(twoDayRange)))).thenReturn(150.0);

        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(150.0, fee, 0.01);
    }

    @Test
    public void testGetCancellationFeeWhenStartDateIsToday() {
        DateRange range = new DateRange(LocalDate.now(), LocalDate.now().plusDays(5));
        DateRange twoDayRange = new DateRange(range.getStart(), range.getStart().plusDays(2));

        when(housingMock.getPrice(argThat(matchesDateRange(twoDayRange)))).thenReturn(200.0);

        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(200.0, fee, 0.01);
    }

    @Test
    public void testGetCancellationFeePastStartDate() {
        DateRange range = new DateRange(LocalDate.now().minusDays(1), LocalDate.now().plusDays(5));
        DateRange twoDayRange = new DateRange(range.getStart(), range.getStart().plusDays(2));

        when(housingMock.getPrice(argThat(matchesDateRange(twoDayRange)))).thenReturn(300.0);

        double fee = freeCancellation.getCancellationFee(range);
        assertEquals(300.0, fee, 0.01);
    }

    // Para que matchee el rango de fechas 
    private static ArgumentMatcher<DateRange> matchesDateRange(DateRange expectedRange) {
        return actualRange -> actualRange.getStart().equals(expectedRange.getStart()) &&
                              actualRange.getEnd().equals(expectedRange.getEnd());
    }
}
