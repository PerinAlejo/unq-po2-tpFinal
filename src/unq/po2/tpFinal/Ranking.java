package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public class Ranking {
	private Ranker ranker;
	private Rankeable ranked;
	private List<CategoryScore> categoryScores;
	private String comment;
	private LocalDate rankedOn;	

	public Ranking(Ranker ranker, Rankeable ranked, String comment, List<CategoryScore> categoryScores) {
		super();
		this.ranker = ranker;		
		this.ranked = ranked;
		this.comment = comment;
		this.rankedOn = LocalDate.now();
		this.categoryScores = categoryScores;
	}
	
	public List<CategoryScore> getScores() {
		return categoryScores;
	}
	
	public String getComment() {
		return comment;
	}
	
	public Ranker getRanker() {
		return ranker;
	}
	
	public Rankeable getRanked() {
		return ranked;
	}

	public LocalDate getRankedOn() {
		return rankedOn;
	}
}