package unq.po2.tpFinal.domain;

import java.time.LocalDate;
import java.util.List;

import unq.po2.tpFinal.interfaces.PaymentMethod;

public class Booking {
	private Housing housing;
	private Tenant tenant;
	private DateRange range;
	private PaymentMethod paymentMethod;
	public Booking(Housing housing, Tenant tenant, DateRange range, PaymentMethod paymentMethod) {
		super();
		this.housing = housing;		
		this.tenant = tenant;
		this.range = range;
		this.paymentMethod = paymentMethod;
	}

	public Tenant getTenant() {
		return tenant;
	}	
	
	public Housing getHousing() {
        return this.housing;
    }    

    public Owner getOwner(){
        return this.housing.getOwner();
    }
    
    public DateRange getRange() {
    	return this.range;
    }
    
    public void checkOut(List<Ranking> rankings) {
    	rankings.forEach(ranking -> ranking.getRanker().rank(ranking));
    }

	public boolean startsAfter(LocalDate date) {
		return this.range.startsAfter(date);
	}

	public boolean isOnCity(City city) { 
		return this.housing.isLocatedIn(city);
	}
	
	public City getCity() {
		return this.housing.getCity();
	}

	public boolean isBookedOn(LocalDate date) {
		return this.range.contains(date);
	}
	
	public boolean isBookedOnRange(DateRange range) {
		return this.isBookedOn(range.getStart()) || this.isBookedOn(range.getEnd());
	}
	
	public void cancelBook() {
		this.paymentMethod.applyCharge(this.housing.getCancelationFee(this.range));
		this.housing.getDefaultPaymentMethod().applyCharge(this.housing.getCancelationFee(this.range));
		this.getOwner().cancelBook(this);
	}	
}
