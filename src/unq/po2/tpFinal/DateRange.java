package unq.po2.tpFinal;

import java.time.LocalDate;

public class DateRange {
    private LocalDate start;
    private LocalDate end;

    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public long getOverlapDays(DateRange other) {
        LocalDate overlapStart = start.isAfter(other.getStart()) ? start : other.getStart();
        LocalDate overlapEnd = end.isBefore(other.getEnd()) ? end : other.getEnd();
        if (overlapStart.isAfter(overlapEnd)) {
            return 0;
        }
        return overlapEnd.toEpochDay() - overlapStart.toEpochDay() + 1;
    }
}