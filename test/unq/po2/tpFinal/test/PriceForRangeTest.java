package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.PriceForRange;

public class PriceForRangeTest {

    private DateRange rangeMock;
    private PriceForRange priceForRange;

    @BeforeEach
    public void setUp() {
        rangeMock = mock(DateRange.class);
        priceForRange = new PriceForRange(100.0, rangeMock);
    }

    @Test
    public void testGetPriceForRangeWithFullOverlap() {
        DateRange queryRange = mock(DateRange.class);
        when(rangeMock.getOverlapDays(queryRange)).thenReturn(10L);

        double result = priceForRange.getPriceForRange(queryRange);

        assertEquals(1000.0, result);
    }

    @Test
    public void testGetPriceForRangeWithPartialOverlap() {
        DateRange queryRange = mock(DateRange.class);
        when(rangeMock.getOverlapDays(queryRange)).thenReturn(5L);

        double result = priceForRange.getPriceForRange(queryRange);

        assertEquals(500.0, result);
    }

    @Test
    public void testGetPriceForRangeWithNoOverlap() {
        DateRange queryRange = mock(DateRange.class);
        when(rangeMock.getOverlapDays(queryRange)).thenReturn(0L);

        double result = priceForRange.getPriceForRange(queryRange);

        assertEquals(0.0, result);
    }
}
