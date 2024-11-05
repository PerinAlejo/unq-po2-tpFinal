package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public class Comment {
    private String text;
    private LocalDate commentedOn;
    private Ranker by;   
    private List<CategoryScore> scores;

    private Comment(String text, LocalDate commentedOn, Ranker by, List<CategoryScore> scores) {
        this.text = text;
        this.commentedOn = commentedOn;
        this.by = by;        
        this.scores = scores;
    }
    
    
    public static Comment fromRanking(Ranking ranking) {
    	return new Comment(
    			ranking.getComment(),
    			ranking.getRankedOn(),
    			ranking.getRanker(),
    			ranking.getScores());
    }
}
