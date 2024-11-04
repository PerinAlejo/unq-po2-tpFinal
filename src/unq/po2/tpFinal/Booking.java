package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

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
	
	public void cancelBook() {
		this.tenant.addCancelationFee(this.housing.getCancelationFee());
		this.getOwner().cancelBook(this);
	}
}
