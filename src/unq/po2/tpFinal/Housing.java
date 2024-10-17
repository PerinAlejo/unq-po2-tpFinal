package unq.po2.tpFinal;

import java.util.List;

public class Housing {
	private HousingType housingType;
	private float area;
	private Address address;
	private List<Service> services;
	private int capacity;
	private List<Picture> pictures;
	private HousingStayDetails stayDetails;
	private List<PaymentMethod> paymentMethods;
	private PriceCalculatorInterface priceCalculator;
	
	public Housing(HousingType housingType, float area, Address address, List<Service> services, int capacity,
			List<Picture> pictures, HousingStayDetails stayDetails, List<PaymentMethod> paymentMethods,
			PriceCalculatorInterface priceCalculator) {	
		this.housingType = housingType;
		this.area = area;
		this.address = address;
		this.services = services;
		this.capacity = capacity;
		this.pictures = pictures;
		this.stayDetails = stayDetails;
		this.paymentMethods = paymentMethods;
		this.priceCalculator = priceCalculator;
	}
	
	public double getPrice(DateRange range) {
		return this.priceCalculator.getPrice(range);
	}

	public int getCapacity() {
		return this.capacity;
	}

	public Address getAddress() {
		// TODO Auto-generated method stub
		return this.address;
	}

	public boolean isLocatedIn(City city) {
		return this.address.getCity().equals(city);
	}

	public boolean isAvailable(DateRange dateRange) {
		return true;
	}
	
}
