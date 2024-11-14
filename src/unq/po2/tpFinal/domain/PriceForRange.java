package unq.po2.tpFinal.domain;

public class PriceForRange {
	private double price;
	private DateRange range;
	public PriceForRange(double price, DateRange range) {	
		this.price = price;
		this.range = range;
	}
	
	public double getPriceForRange(DateRange range) {
		return this.range.getOverlapDays(range) * this.price;
	}
}
