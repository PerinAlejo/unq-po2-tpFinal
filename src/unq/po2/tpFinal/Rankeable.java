package unq.po2.tpFinal;

import java.util.List;

public interface Rankeable {
	void addRanking(Ranking ranking);
	List<Ranking> getRankings();
}
