package unq.po2.tpFinal.search;

import java.util.List;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;

public class DateRangeFilter implements SearchFilter {

	DateRange dateRange;
	
	public DateRangeFilter(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	@Override
	public List<Housing> filter(List<Housing> housingList) {
		
		return housingList.stream().filter(housing -> housing.isAvailable(this.dateRange)).toList();
	}

}
