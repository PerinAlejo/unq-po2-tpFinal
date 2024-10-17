package unq.po2.tpFinal;

import java.util.List;

public class MaxPriceFilter implements SearchFilter {

	double maxPrice;
	DateRange dateRange;

	public MaxPriceFilter(double maxPrice, DateRange dateRange) {
		this.maxPrice = maxPrice;
		this.dateRange = dateRange;
	}
	
	@Override
	public List<Housing> filter(List<Housing> housingList) {
		return housingList.stream().filter(housing -> housing.getPrice(this.dateRange) <= this.maxPrice).toList();
	}
}
