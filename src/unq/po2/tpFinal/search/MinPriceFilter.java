package unq.po2.tpFinal.search;

import java.util.List;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;

public class MinPriceFilter implements SearchFilter {

	double minPrice;
	DateRange dateRange;
	
	public MinPriceFilter(double minPrice, DateRange dateRange) {
		this.minPrice = minPrice;
		this.dateRange = dateRange;
	}
	
	@Override
	public List<Housing> filter(List<Housing> housingList) {
		return housingList.stream().filter(housing -> housing.getPrice(this.dateRange) >= this.minPrice).toList();
	}

}