package unq.po2.tpFinal;

public class NoCancellation extends CancellationPolicy {

	public NoCancellation(Housing housing) {
		super(housing);
	}

	@Override
	public double getCancellationFee(DateRange range) {
		return this.getHousing().getPrice(range);
	}

}
