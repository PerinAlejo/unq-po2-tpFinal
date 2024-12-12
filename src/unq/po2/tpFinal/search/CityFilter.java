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
	public boolean matches(Housing housing) {
		return housing.isLocatedIn(city);
	}

}
