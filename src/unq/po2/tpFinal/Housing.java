package unq.po2.tpFinal;

import java.util.List;

public class Housing implements Rankeable{
	private HousingType housingType;
	private float area;
	private Address address;
	private List<Service> services;
	private int capacity;
	private List<Picture> pictures;
	private HousingStayDetails stayDetails;
	private List<PaymentMethod> paymentMethods;
	private PriceCalculatorInterface priceCalculator;
	private int totalRatings;
    private int numberOfRatings;
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
		return this.address.getCity().equals(city);
	}

	public boolean isAvailable(DateRange dateRange) {
		return true;
	}
	
	@Override
    public void addRating(int rating) {
        totalRatings += rating;
        numberOfRatings++;
    }

    @Override
    public double getAverageRating() {
        return numberOfRatings > 0 ? (double) totalRatings / numberOfRatings : 0.0;
    }

    public Owner getOwner() {        
        return this.owner;
    }
}
