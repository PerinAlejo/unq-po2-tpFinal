package unq.po2.tpFinal;

import java.util.List;

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
