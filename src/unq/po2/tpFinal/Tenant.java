package unq.po2.tpFinal;

public class Tenant extends User implements Rankeable, Ranker {
	private int totalRatings = 0;
	private int numberOfRatings = 0;

	public Tenant(String fullName, String email, String phoneNumber) {
		super(fullName, email, phoneNumber);
	}
	
	@Override
	public void rank(Rankeable rankeable, int rating) {
		rankeable.addRating(rating);
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

}
