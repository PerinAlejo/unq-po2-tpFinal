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
		List<Housing> filteredHousing = new ArrayList<>();

        for (Housing housing : housingList) {
            // Verifica si la instancia cumple con todos los filtros
            boolean matchesAll = filterList.stream().allMatch(filter -> filter.test(housing));
            if (matchesAll) {
                filteredHousing.add(housing);
            }
        }

        return filteredHousing;
	}
	
}
