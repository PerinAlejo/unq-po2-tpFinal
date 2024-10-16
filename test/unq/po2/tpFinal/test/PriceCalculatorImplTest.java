package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.DateRange;
import unq.po2.tpFinal.PriceCalculatorImpl;
import unq.po2.tpFinal.PriceForRange;

public class PriceCalculatorImplTest {

    @Test
    public void testSinglePriceRangeFullOverlap() {
        PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10)
        );
        double expectedPrice = 100.0 * 10;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testSinglePriceRangePartialOverlap() {
        PriceForRange priceForRange = new PriceForRange(150.0, new DateRange(
                LocalDate.of(2023, 1, 5),
                LocalDate.of(2023, 1, 15)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 10),
                LocalDate.of(2023, 1, 20)
        );
        double expectedPrice = 150.0 * 6;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testMultiplePriceRangesFullOverlap() {
        PriceForRange price1 = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 5)
        ));
        PriceForRange price2 = new PriceForRange(200.0, new DateRange(
                LocalDate.of(2023, 1, 6),
                LocalDate.of(2023, 1, 10)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10)
        );
        double expectedPrice = (100.0 * 5) + (200.0 * 5);

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testMultiplePriceRangesPartialOverlap() {
        PriceForRange price1 = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 5)
        ));
        PriceForRange price2 = new PriceForRange(150.0, new DateRange(
                LocalDate.of(2023, 1, 4),
                LocalDate.of(2023, 1, 8)
        ));
        PriceForRange price3 = new PriceForRange(200.0, new DateRange(
                LocalDate.of(2023, 1, 10),
                LocalDate.of(2023, 1, 12)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2, price3));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 3),
                LocalDate.of(2023, 1, 10)
        );

        double expectedPrice = (100.0 * 3) + (150.0 * 5) + (200.0 * 1);

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testNoOverlap() {
        PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 5)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 6),
                LocalDate.of(2023, 1, 10)
        );
        double expectedPrice = 0.0;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testEmptyPriceForRanges() {
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Collections.emptyList());

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10)
        );
        double expectedPrice = 0.0;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testOverlappingPriceForRanges() {
        PriceForRange price1 = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 5)
        ));
        PriceForRange price2 = new PriceForRange(200.0, new DateRange(
                LocalDate.of(2023, 1, 3),
                LocalDate.of(2023, 1, 7)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 7)
        );

        double expectedPrice = (100.0 * 5) + (200.0 * 5);

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testNegativePrices() {
        PriceForRange priceForRange = new PriceForRange(-50.0, new DateRange(
                LocalDate.of(2023, 1, 5),
                LocalDate.of(2023, 1, 10)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 7),
                LocalDate.of(2023, 1, 9)
        );
        double expectedPrice = -50.0 * 3;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testZeroLengthQueryRange() {
        PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 5),
                LocalDate.of(2023, 1, 10)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 7),
                LocalDate.of(2023, 1, 7)
        );
        double expectedPrice = 100.0 * 1;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testFractionalPrices() {
        PriceForRange priceForRange = new PriceForRange(99.99, new DateRange(
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 2, 28)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 2, 10),
                LocalDate.of(2023, 2, 20)
        );
        double expectedPrice = 99.99 * 11;

        assertEquals(expectedPrice, calculator.getPrice(queryRange), 0.001);
    }

    @Test
    public void testOverlappingPriceForRangesWithDifferentPrices() {
        PriceForRange price1 = new PriceForRange(80.0, new DateRange(
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 3, 10)
        ));
        PriceForRange price2 = new PriceForRange(120.0, new DateRange(
                LocalDate.of(2023, 3, 5),
                LocalDate.of(2023, 3, 15)
        ));
        PriceForRange price3 = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 3, 8),
                LocalDate.of(2023, 3, 12)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2, price3));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 3, 15)
        );

        double expectedPrice = (80.0 * 10) + (120.0 * 11) + (100.0 * 5);

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }

    @Test
    public void testQueryRangeOutsidePriceForRanges() {
        PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10)
        ));
        PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

        DateRange queryRange = new DateRange(
                LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 20)
        );
        double expectedPrice = 0.0;

        assertEquals(expectedPrice, calculator.getPrice(queryRange));
    }
}