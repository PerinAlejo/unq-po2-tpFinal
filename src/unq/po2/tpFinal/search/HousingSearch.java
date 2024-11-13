package unq.po2.tpFinal.search;

import java.util.List;

import unq.po2.tpFinal.domain.Housing;

public class HousingSearch implements SearchFilter{
	
	List<SearchFilter> filterList;

	public HousingSearch (List<SearchFilter> filterList) {
		this.filterList = filterList;
	}
	
	@Override
	public List<Housing> filter(List<Housing> housingList) {
		for (SearchFilter filtro : this.filterList) {
			housingList = filtro.filter(housingList);
		}
		return housingList;
	}
	
}
