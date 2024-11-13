package unq.po2.tpFinal.interfaces;

import java.util.List;

import unq.po2.tpFinal.domain.Ranking;

public interface Rankeable {
	void addRanking(Ranking ranking);
	List<Ranking> getRankings();
}
