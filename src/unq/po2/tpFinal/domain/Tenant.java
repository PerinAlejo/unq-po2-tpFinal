package unq.po2.tpFinal.domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import unq.po2.tpFinal.interfaces.HousingObserver;
import unq.po2.tpFinal.interfaces.PaymentMethod;
import unq.po2.tpFinal.interfaces.Rankeable;
import unq.po2.tpFinal.interfaces.Ranker;

public class Tenant extends User implements Rankeable, Ranker {
	private List<Ranking> rankings;	
	
	public Tenant(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super(fullName, email, phoneNumber, createdOn);
		rankings = new ArrayList<Ranking>();
	}	

	@Override
	public void addRanking(Ranking ranking) {
		rankings.add(ranking);
	}

	@Override
	public List<Ranking> getRankings() {
		return rankings;
	}

	@Override
	public void rank(Ranking ranking) {
		ranking.getRanked().addRanking(ranking);
	}
	
	public void book(Housing housing, DateRange range, PaymentMethod paymentMethod) throws Exception {
		if(housing.isAvailable(range)) {
			Booking booking = new Booking(housing, this, range, paymentMethod);		
			housing.addPendingBooking(booking);
		} else {
			throw new Exception("La casa está ocupada en el período seleccionada");
		}		
	}
	
	public void conditionalBook(Housing housing, DateRange range, PaymentMethod paymentMethod) {
		Booking booking = new Booking(housing, this, range, paymentMethod);		
		housing.addPendingBooking(booking);
	}
}