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
	public boolean test(Housing housing) {
		return housing.getCapacity() >= this.capacity;
	}
	

}
