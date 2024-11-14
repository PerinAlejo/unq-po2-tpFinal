package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.DateRange;

public class DateRangeTest {

    @Test
    public void testFullOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), 10);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 1), 10);
        assertEquals(11, range1.getOverlapDays(range2));
    }

    @Test
    public void testPartialOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 10);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 10), 11);
        assertEquals(6, range1.getOverlapDays(range2));
    }

    @Test
    public void testNoOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), 4);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 6), 5);
        assertEquals(0, range1.getOverlapDays(range2));
    }

    @Test
    public void testOneInsideAnother() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), 20);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), 11);
        assertEquals(12, range1.getOverlapDays(range2));
    }

    @Test
    public void testSameStartDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 6);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), 4);
        assertEquals(5, range1.getOverlapDays(range2));
    }

    @Test
    public void testSameEndDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 6);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 8), 3);
        assertEquals(4, range1.getOverlapDays(range2));
    }

    @Test
    public void testZeroLengthRange() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 0);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), 1);
        assertEquals(1, range1.getOverlapDays(range2));
    }

    @Test
    public void testAdjacentRanges() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), 4);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 6), 5);
        assertEquals(0, range1.getOverlapDays(range2));
    }

    @Test
    public void testSingleDayOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 5);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 10), 6);
        assertEquals(1, range1.getOverlapDays(range2));
    }

    @Test
    public void testStartDateAfterEndDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 10), -5);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), 6);
        assertEquals(0, range1.getOverlapDays(range2));
    }

    @Test
    public void testOverlaps() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 6);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 8), 8);
        assertTrue(range1.overlaps(range2));
    }

    @Test
    public void testOverlapsWithNoOverlap() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 5);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 11), 5);
        assertFalse(range1.overlaps(range2));
    }

    @Test
    public void testOverlapsWithAdjacentRanges() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 6);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 10), 5);
        assertTrue(range1.overlaps(range2));
    }

    @Test
    public void testStartsAfterTrue() {
        DateRange range = new DateRange(LocalDate.of(2023, 1, 10), 5);
        LocalDate date = LocalDate.of(2023, 1, 5);
        assertTrue(range.startsAfter(date));
    }

    @Test
    public void testStartsAfterFalse() {
        DateRange range = new DateRange(LocalDate.of(2023, 1, 5), 10);
        LocalDate date = LocalDate.of(2023, 1, 10);
        assertFalse(range.startsAfter(date));
    }

    @Test
    public void testContainsDateTrue() {
        DateRange range = new DateRange(LocalDate.of(2023, 1, 5), 10);
        LocalDate date = LocalDate.of(2023, 1, 10);
        assertTrue(range.contains(date));
    }

    @Test
    public void testContainsDateFalse() {
        DateRange range = new DateRange(LocalDate.of(2023, 1, 5), 10);
        LocalDate date = LocalDate.of(2023, 1, 20);
        assertFalse(range.contains(date));
    }

    @Test
    public void testContainsDateOnBoundaryFalse() {
        DateRange range = new DateRange(LocalDate.of(2023, 1, 5), 10);
        LocalDate date = LocalDate.of(2023, 1, 5);
        assertFalse(range.contains(date));
    }
    
    @Test
    public void testOverlapsWithSameEndDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), 10);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), 6);
        assertTrue(range1.overlaps(range2));
    }

    @Test
    public void testOverlapsWithSameStartDate() {
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 5), 10);
        DateRange range2 = new DateRange(LocalDate.of(2023, 1, 5), 5);
        assertTrue(range1.overlaps(range2));
    }
}
