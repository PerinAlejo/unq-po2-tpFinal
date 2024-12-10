package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.PriceForRange;
import unq.po2.tpFinal.implementations.PriceCalculatorImpl;

public class PriceCalculatorImplTest {

	private Housing housingMock;

	@BeforeEach
	public void setUp() {
		housingMock = mock(Housing.class);
	}

	@Test
	public void testSinglePriceRangeFullOverlap() {
		PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 9);
		double expectedPrice = 100.0 * 10;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testSinglePriceRangePartialOverlap() {
		PriceForRange priceForRange = new PriceForRange(150.0, new DateRange(LocalDate.of(2023, 1, 5), 10));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 10), 10);
		double expectedPrice = 150.0 * 6;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testMultiplePriceRangesFullOverlap() {
		PriceForRange price1 = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 4));
		PriceForRange price2 = new PriceForRange(200.0, new DateRange(LocalDate.of(2023, 1, 6), 4));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 9);
		double expectedPrice = (100.0 * 5) + (200.0 * 5);

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testMultiplePriceRangesPartialOverlap() {
		PriceForRange price1 = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 4));
		PriceForRange price2 = new PriceForRange(150.0, new DateRange(LocalDate.of(2023, 1, 4), 4));
		PriceForRange price3 = new PriceForRange(200.0, new DateRange(LocalDate.of(2023, 1, 10), 2));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2, price3));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 3), 7);

		double expectedPrice = (100.0 * 3) + (150.0 * 5) + (200.0 * 1);

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testNoOverlap() {
		PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 4));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 6), 4);
		double expectedPrice = 0.0;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testEmptyPriceForRanges() {
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Collections.emptyList());

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 10);
		double expectedPrice = 0.0;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testOverlappingPriceForRanges() {
		PriceForRange price1 = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 4));
		PriceForRange price2 = new PriceForRange(200.0, new DateRange(LocalDate.of(2023, 1, 3), 4));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 6);

		double expectedPrice = (100.0 * 5) + (200.0 * 5);

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testZeroLengthQueryRange() {
		PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 5), 6));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 7), 0);
		double expectedPrice = 100.0 * 1;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testFractionalPrices() {
		PriceForRange priceForRange = new PriceForRange(99.99, new DateRange(LocalDate.of(2023, 2, 1), 27));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 2, 10), 10);
		double expectedPrice = 99.99 * 11;

		assertEquals(expectedPrice, calculator.getPrice(queryRange), 0.001);
	}

	@Test
	public void testOverlappingPriceForRangesWithDifferentPrices() {
		PriceForRange price1 = new PriceForRange(80.0, new DateRange(LocalDate.of(2023, 3, 1), 9));
		PriceForRange price2 = new PriceForRange(120.0, new DateRange(LocalDate.of(2023, 3, 5), 10));
		PriceForRange price3 = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 3, 8), 4));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(price1, price2, price3));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 3, 1), 14);
		double expectedPrice = (80.0 * 10) + (120.0 * 11) + (100.0 * 5);

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void testQueryRangeOutsidePriceForRanges() {
		PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(Arrays.asList(priceForRange));

		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 11), 9);
		double expectedPrice = 0.0;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void addsAPrice() {
		PriceForRange priceForRange = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(new ArrayList<PriceForRange>());
		calculator.addPrice(priceForRange, housingMock);
		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 9);
		double expectedPrice = 100.0 * 10;

		assertEquals(expectedPrice, calculator.getPrice(queryRange));
	}

	@Test
	public void updatesAPriceAndNotifiesPriceDrop() {
		PriceForRange priceForRange1 = new PriceForRange(0.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceForRange priceForRange2 = new PriceForRange(100.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(new ArrayList<PriceForRange>());
		calculator.addPrice(priceForRange1, housingMock);
		calculator.addPrice(priceForRange2, housingMock);
		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 9);

		assertEquals(1000.0, calculator.getPrice(queryRange));
		verify(housingMock, never()).priceDropped(priceForRange2);
	}
	
	@Test
	public void updatesAPriceAndDoesNotNotifyPriceDrop() {
		PriceForRange priceForRange1 = new PriceForRange(10.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceForRange priceForRange2 = new PriceForRange(0.0, new DateRange(LocalDate.of(2023, 1, 1), 9));
		PriceCalculatorImpl calculator = new PriceCalculatorImpl(new ArrayList<PriceForRange>());
		calculator.addPrice(priceForRange1, housingMock);
		calculator.addPrice(priceForRange2, housingMock);
		DateRange queryRange = new DateRange(LocalDate.of(2023, 1, 1), 9);

		assertEquals(0.0, calculator.getPrice(queryRange));
		verify(housingMock).priceDropped(priceForRange2);
	}

}
