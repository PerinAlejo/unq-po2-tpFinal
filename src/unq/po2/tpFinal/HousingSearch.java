package unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
