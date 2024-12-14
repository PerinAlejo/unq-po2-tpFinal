package unq.po2.tpFinal.search;

import java.util.ArrayList;
import java.util.List;

import unq.po2.tpFinal.domain.Housing;

public class HousingSearch {
	
	List<SearchFilter> filterList;

	public HousingSearch (List<SearchFilter> filterList) {
		this.filterList = filterList;
	}
	
	public List<Housing> filter(List<Housing> housingList) {
	    return housingList.stream()
	            .filter(housing -> filterList.stream().allMatch(filter -> filter.matches(housing)))
	            .toList();
	}
	
}
