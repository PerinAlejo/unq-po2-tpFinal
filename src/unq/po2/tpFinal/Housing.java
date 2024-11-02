package unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class Housing implements Rankeable {
	private HousingType housingType;
	private float area;
	private Address address;
	private List<Service> services;
	private int capacity;
	private List<Picture> pictures;
	private HousingStayDetails stayDetails;
	private List<PaymentMethod> paymentMethods;
	private PriceCalculatorInterface priceCalculator;
	private List<Ranking> rankings;
	private Owner owner;
	
	public Housing(HousingType housingType, float area, Address address, List<Service> services, int capacity,
			List<Picture> pictures, HousingStayDetails stayDetails, List<PaymentMethod> paymentMethods,
			PriceCalculatorInterface priceCalculator, Owner owner) {	
		this.housingType = housingType;
		this.area = area;
		this.address = address;
		this.services = services;
		this.capacity = capacity;
		this.pictures = pictures;
		this.stayDetails = stayDetails;
		this.paymentMethods = paymentMethods;
		this.priceCalculator = priceCalculator;
		this.owner = owner;
		this.rankings = new ArrayList<Ranking>();
	}
	
	public double getPrice(DateRange range) {
		return this.priceCalculator.getPrice(range);
	}

	public int getCapacity() {
		return this.capacity;
	}

	public Address getAddress() {		
		return this.address;
	}

	public boolean isLocatedIn(City city) {
		return this.getCity().equals(city);
	}

	public boolean isAvailable(DateRange dateRange) {
		return true;
	}

    public Owner getOwner() {        
        return this.owner;
    }
    
    public List<Ranking> getRankings(){
    	return this.rankings;
    }

	@Override
	public void addRanking(Ranking ranking) {
		this.rankings.add(ranking);
	}
	
	public City getCity() {
		return this.address.getCity();
	}
}
