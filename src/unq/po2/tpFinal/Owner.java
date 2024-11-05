package unq.po2.tpFinal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Owner extends User implements Rankeable, Ranker {
	private List<Ranking> rankings;
	private List<Booking> rentals;
	private BookingAcceptanceStrategy bookingAcceptanceStrategy;
	private List<BookingAcceptedObserver> bookingObservers;

	public Owner(String fullName, String email, String phoneNumber, LocalDateTime createdOn, BookingAcceptanceStrategy bookingAcceptanceStrategy,List<BookingAcceptedObserver> bookingObservers) {
		super(fullName, email, phoneNumber, createdOn); 
		rentals = new ArrayList<Booking>();
		rankings = new ArrayList<Ranking>();
		this.bookingAcceptanceStrategy = bookingAcceptanceStrategy;
		this.bookingObservers = bookingObservers;
	}

	public void addRental(Booking rental){
		this.rentals.add(rental);
	}
	
	public List<Booking> getRentals(){
		return this.rentals;
	}

	@Override
	public void addRanking(Ranking ranking) {
		this.rankings.add(ranking);
	}
 
	@Override
	public List<Ranking> getRankings(){
    	return this.rankings;
    }

	@Override
	public void rank(Ranking ranking) {
		ranking.getRanked().addRanking(ranking);
	}

	public void accept(Booking booking) {
		if(this.bookingAcceptanceStrategy.isAcceptable(booking)) {
			this.bookingObservers.forEach(observer -> observer.notifyBookingAccepted(booking));
		}
	}
	
	public void removalRental(Booking booking) {
		this.rentals.remove(booking);
	}
	
	public void cancelBook(Booking booking) {
		this.removalRental(booking);
		
	}
}