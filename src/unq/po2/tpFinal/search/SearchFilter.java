package unq.po2.tpFinal.search;

import java.util.List;

import unq.po2.tpFinal.domain.Housing;

public interface SearchFilter {

	List<Housing> filter(List<Housing> housingList);
	
}
