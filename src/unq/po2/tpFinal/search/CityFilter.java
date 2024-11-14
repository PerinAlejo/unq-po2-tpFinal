package unq.po2.tpFinal.search;

import java.util.List;

import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.Housing;

public class CityFilter implements SearchFilter{

	City city;
	
	public CityFilter( City city) {
		this.city = city;
	}
	
	@Override
	public List<Housing> filter(List<Housing> housingList) {

		return housingList.stream().filter(housing -> housing.isLocatedIn(city)).toList();
	}

}
