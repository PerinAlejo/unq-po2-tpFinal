package unq.po2.tpFinal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tenant extends User implements Rankeable, Ranker {
	private List<Ranking> rankings;
	public Tenant(String fullName, String email, String phoneNumber, LocalDateTime createdOn, RankingProvider rankingProvider) {
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
}