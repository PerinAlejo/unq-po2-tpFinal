package unq.po2.tpFinal;

import java.util.List;

public interface RankingProvider {
	List<Ranking> askForRankings(Rental rental);
}