package unq.po2.tpFinal.domain;

public class Address {
	private Country country;
	private City city;
	private String houseLocation;
	
	
	public Address(Country country, City city, String houseLocation) {	
		this.country = country;
		this.city = city;
		this.houseLocation = houseLocation;
	}

	public String getHouseLocation() {
		return houseLocation;
	}
	
	public City getCity() {
		return city;
	}

	public Country getCountry() {
		return country;
	}
}
