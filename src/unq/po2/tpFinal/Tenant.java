package unq.po2.tpFinal;

public class Tenant extends User implements Rankable, Rank {
	private int score;
	private int totalReviews;

	public Tenant(String fullName, String email, String phoneNumber) {
		super(fullName, email, phoneNumber);
	}
	
	@Override
	public void rankEntity(Rankable entity, int score) {
		entity.calculateRanking(score);
	}

	@Override
	public double calculateRanking(int newScore) {
		this.score += newScore;
		this.totalReviews++;
		return (double) this.score / this.totalReviews;
	}

}
