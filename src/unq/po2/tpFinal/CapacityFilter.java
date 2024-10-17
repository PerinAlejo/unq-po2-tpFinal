package unq.po2.tpFinal;

import java.util.List;
import java.util.stream.Collectors;

public class CapacityFilter implements SearchFilter {

	int capacity ;
	
	public CapacityFilter(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public List<Housing> filter(List<Housing> housingList) {
		return housingList.stream().filter(housing -> housing.getCapacity() >= this.capacity).collect(Collectors.toList());
		
	}
	

}
