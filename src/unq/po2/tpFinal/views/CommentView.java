package unq.po2.tpFinal.views;

import java.time.LocalDate;
import java.util.List;

import unq.po2.tpFinal.domain.CategoryScore;
import unq.po2.tpFinal.domain.Ranker;
import unq.po2.tpFinal.domain.Ranking;

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
    	return new CommentView(
    			ranking.getComment(),
    			ranking.getRankedOn(),
    			ranking.getRanker(),
    			ranking.getScores());
    }
}
