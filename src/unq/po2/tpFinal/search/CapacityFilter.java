package unq.po2.tpFinal.search;

import java.util.List;
import java.util.stream.Collectors;

import unq.po2.tpFinal.domain.Housing;

public class CapacityFilter implements SearchFilter {

	int capacity ;
	
	public CapacityFilter(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public List<Housing> filter(List<Housing> housingList) {
		return housingList.stream().filter(housing -> housing.getCapacity() >= this.capacity).toList();
		
	}
	

}
