package unq.po2.tpFinal.domain;

import java.util.ArrayList;
import java.util.List;

import unq.po2.tpFinal.interfaces.PaymentMethod;
import unq.po2.tpFinal.interfaces.PriceCalculatorInterface;
import unq.po2.tpFinal.interfaces.Rankeable;
import unq.po2.tpFinal.interfaces.Service;

public class Housing implements Rankeable {
	private HousingType housingType;
	private float area;
	private Address address;
	private List<Service> services;
	private int capacity;
	private List<Picture> pictures;
	private HousingStayDetails stayDetails;
	private List<PaymentMethod> paymentMethods;
	private PaymentMethod defaultPaymentMethod;
	private PriceCalculatorInterface priceCalculator;
	private List<Ranking> rankings;
	private Owner owner;
	private CancellationPolicy cancellationPolicy;
	
	public Housing(HousingType housingType, float area, Address address, List<Service> services, int capacity,
			List<Picture> pictures, HousingStayDetails stayDetails, List<PaymentMethod> paymentMethods,
			PriceCalculatorInterface priceCalculator, Owner owner, CancellationPolicy cancellationPolicy) {	
		this.housingType = housingType;
		this.area = area;
		this.address = address;
		this.services = services;
		this.capacity = capacity;
		this.pictures = pictures;
		this.stayDetails = stayDetails;
		this.paymentMethods = paymentMethods;
		this.defaultPaymentMethod = paymentMethods.getFirst();
		this.priceCalculator = priceCalculator;
		this.owner = owner;
		this.rankings = new ArrayList<Ranking>();
		this.cancellationPolicy = cancellationPolicy;
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

	public double getCancelationFee(DateRange range) {
		return this.cancellationPolicy.getCancellationFee(range);
	}
	
	public PaymentMethod getDefaultPaymentMethod() {
		return this.defaultPaymentMethod;
	}
	
	public HousingType getHousingType() {
		return this.housingType;
	}
}
