package unq.po2.tpFinal.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import unq.po2.tpFinal.interfaces.BookingAcceptanceObservable;
import unq.po2.tpFinal.interfaces.BookingAcceptedObserver;
import unq.po2.tpFinal.interfaces.PaymentMethod;

public class Booking implements BookingAcceptanceObservable {
	private Housing housing;
	private Tenant tenant;
	private DateRange range;
	private PaymentMethod paymentMethod;
	private List<BookingAcceptedObserver> bookingObservers;
	
	public Booking(Housing housing, Tenant tenant, DateRange range, PaymentMethod paymentMethod) {
		super();
		this.housing = housing;		
		this.tenant = tenant;
		this.range = range;
		this.paymentMethod = paymentMethod;
		this.bookingObservers = new ArrayList<BookingAcceptedObserver>();
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
    
    @Override
	public void addObserver(BookingAcceptedObserver observer) {
    	this.bookingObservers.add(observer);
    }
    
    @Override
	public void removeObserver(BookingAcceptedObserver observer) {
    	this.bookingObservers.remove(observer);
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
		this.paymentMethod.applyCharge(this.housing.getCancelationFee(this.range));
		this.housing.getDefaultPaymentMethod().applyCharge(this.housing.getCancelationFee(this.range));
		this.getOwner().cancelBook(this);
	}
	
	public void acceptBook() {		
		this.bookingObservers.forEach(observer -> observer.notifyBookingAccepted(this));
	}
}
