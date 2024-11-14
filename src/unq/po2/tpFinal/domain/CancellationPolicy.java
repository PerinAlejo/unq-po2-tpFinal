package unq.po2.tpFinal.domain;

public abstract class CancellationPolicy {

	private Housing housing;
		
	public CancellationPolicy(Housing housing) {
		this.housing = housing;
	}
	
	public Housing getHousing() {
		return this.housing;
	}

	public abstract double getCancellationFee(DateRange range);
	
}
