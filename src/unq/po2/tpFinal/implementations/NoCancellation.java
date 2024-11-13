package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.domain.CancellationPolicy;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;

public class NoCancellation extends CancellationPolicy {

	public NoCancellation(Housing housing) {
		super(housing);
	}

	@Override
	public double getCancellationFee(DateRange range) {
		return this.getHousing().getPrice(range);
	}

}
