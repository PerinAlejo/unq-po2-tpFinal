package unq.po2.tpFinal;

import unq.po2.tpFinal.*;

public class Owner extends User implements Rankeable, Ranker {
	private int totalRatings;
	private int numberOfRatings;

	public Owner(String fullName, String email, String phoneNumber) {
		super(fullName, email, phoneNumber); 
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
