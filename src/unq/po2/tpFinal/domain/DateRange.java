package unq.po2.tpFinal.domain;

import java.time.LocalDate;

public class DateRange {
	private LocalDate start;
	private int duration;	
	

	public DateRange(LocalDate start, int duration) {
		this.start = start;
		this.duration = duration;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return start.plusDays(duration);
	}

	public long getOverlapDays(DateRange other) {
		LocalDate overlapStart = start.isAfter(other.getStart()) ? start : other.getStart();
		LocalDate overlapEnd = this.getEnd().isBefore(other.getEnd()) ? this.getEnd() : other.getEnd();
		if (overlapStart.isAfter(overlapEnd)) {
			return 0;
		}
		return overlapEnd.toEpochDay() - overlapStart.toEpochDay() + 1;
	}

	public boolean overlaps(DateRange other) {
		return (start.isBefore(other.getEnd()) || start.isEqual(other.getEnd()))
				&& (this.getEnd().isAfter(other.getStart()) || this.getEnd().isEqual(other.getStart()));
	}

	public boolean startsAfter(LocalDate date) {
		return this.start.isAfter(date);
	}

	public boolean contains(LocalDate date) {
		return start.isBefore(date) && this.getEnd().isAfter(date);
	}

	@Override
	public boolean equals(Object o) {
		return o == this || (o instanceof DateRange && this.isSameDate((DateRange) o));
	}

	private boolean isSameDate(DateRange other) {
		return other.getStart().isEqual(this.getStart()) && other.getEnd().isEqual(this.getEnd());
	}

	public int getDays() {
		return duration;
	}

}