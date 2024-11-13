package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.DateRange;


public class DateRangeTest {

    @Test
    public void testFullOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10));
        assertEquals(10, range1.getOverlapDays(range2));
    }

    @Test
    public void testPartialOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 15));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 20));
        assertEquals(6, range1.getOverlapDays(range2));
    }

    @Test
    public void testNoOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 6), LocalDate.of(2023, 1, 10));
        assertEquals(0, range1.getOverlapDays(range2));
    }

    @Test
    public void testOneInsideAnother() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 20));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 15));
        assertEquals(11, range1.getOverlapDays(range2));
    }

    @Test
    public void testSameStartDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 10));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 8));
        assertEquals(4, range1.getOverlapDays(range2));
    }

    @Test
    public void testSameEndDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 10));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 8), LocalDate.of(2023, 1, 10));
        assertEquals(3, range1.getOverlapDays(range2));
    }

    @Test
    public void testZeroLengthRange() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 5));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 5));
        assertEquals(1, range1.getOverlapDays(range2));
    }

    @Test
    public void testAdjacentRanges() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 6), LocalDate.of(2023, 1, 10));
        assertEquals(0, range1.getOverlapDays(range2));
    }

    @Test
    public void testSingleDayOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 10));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 15));
        assertEquals(1, range1.getOverlapDays(range2));
    }

    @Test
    public void testStartDateAfterEndDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 5));
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 10));
        assertEquals(0, range1.getOverlapDays(range2));
    }
}