package unq.po2.tpFinal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Owner extends User implements Rankeable, Ranker {
	private int totalRatings;
	private int numberOfRatings;
	private List<Rental> rentals;

	public Owner(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super(fullName, email, phoneNumber, createdOn); 
		rentals = new ArrayList<Rental>();
	}

	public void addRental(Rental rental){
		this.rentals.add(rental);
	}
	
	public List<Rental> getRentals(){
		return this.rentals;
	}

	@Override
	public void rank(Rankeable rankeable, int rating) {
		rankeable.addRating(rating);
	}

	@Override
	public void addRating(int rating) {
		this.totalRatings += rating;
		this.numberOfRatings++;
	}
	
	@Override
	public double getAverageRating() {
		return numberOfRatings > 0 ? (double) totalRatings / numberOfRatings : 0.0;
	}
}
