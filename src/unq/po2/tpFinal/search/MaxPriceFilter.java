package unq.po2.tpFinal.search;

import java.util.List;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;

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
