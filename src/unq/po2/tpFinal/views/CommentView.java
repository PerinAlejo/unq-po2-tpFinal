package unq.po2.tpFinal.views;

import java.time.LocalDate;
import java.util.List;

import unq.po2.tpFinal.domain.CategoryScore;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.interfaces.Ranker;

public class CommentView {
	private String text;
	private LocalDate commentedOn;
	private Ranker by;
	private List<CategoryScore> scores;

	private CommentView(String text, LocalDate commentedOn, Ranker by, List<CategoryScore> scores) {
		this.text = text;
		this.commentedOn = commentedOn;
		this.by = by;
		this.scores = scores;
	}

	public static CommentView fromRanking(Ranking ranking) {
		return new CommentView(ranking.getComment(), ranking.getRankedOn(), ranking.getRanker(), ranking.getScores());
	}

	public String getText() {
		return text;
	}

	public LocalDate getCommentedOn() {
		return commentedOn;
	}

	public Ranker getBy() {
		return by;
	}

	public List<CategoryScore> getScores() {
		return scores;
	}

}
