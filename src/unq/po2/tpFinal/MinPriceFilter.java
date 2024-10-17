package unq.po2.tpFinal;

import java.util.List;

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