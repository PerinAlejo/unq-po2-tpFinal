package unq.po2.tpFinal.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.DateRange;

public class HousingSearchBuilder {

	List<SearchFilter> filterList;
	DateRange dateRange;

	//Parametrtos Obligatorios de Busqueda
	public HousingSearchBuilder(City city, DateRange dateRange) {
		this.filterList = new ArrayList<>(Arrays.asList(new CityFilter(city), new DateRangeFilter(dateRange)));
		this.dateRange = dateRange;
	}
	
	//Parametros Extra
	public HousingSearchBuilder setCapacity(int capacity) {
		this.filterList.add(new CapacityFilter(capacity));
		return this;
	}

	public HousingSearchBuilder setMinPrice(double minPrice) {
		this.filterList.add(new MinPriceFilter(minPrice, this.dateRange));
		return this;
	}

	public HousingSearchBuilder setMaxPrice(double maxPrice) {
		this.filterList.add(new MaxPriceFilter(maxPrice, this.dateRange));
		return this;
	}
	
	public SearchFilter build() {
		return new HousingSearch(this.filterList);
	}
	
}
