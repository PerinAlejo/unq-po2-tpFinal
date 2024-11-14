package unq.po2.tpFinal.implementations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import unq.po2.tpFinal.domain.CancellationPolicy;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;

public class FreeCancellation extends CancellationPolicy {
	private static final int MAX_DAYS_FOR_FREE_CANCELLATION = 10;

	public FreeCancellation(Housing housing) {
		super(housing);
	}

	@Override
	public double getCancellationFee(DateRange range) {

		long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), range.getStart());

		if (remainingDays >= MAX_DAYS_FOR_FREE_CANCELLATION) {
			return 0;
		}
		return this.getHousing().getPrice(this.twoDaysRange(range)); // Se calcula el precio para dos dias de estadia
	}

	private DateRange twoDaysRange(DateRange range) {
		return new DateRange(range.getStart(), range.getStart().plusDays(2));
	}
}
