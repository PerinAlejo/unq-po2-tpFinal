package unq.po2.tpFinal;

import java.time.LocalDate;

public class Comment {
    private String text;
    private LocalDate commentedOn;
    private User by;    
    private CategoryScore categoryScore;

    public Comment(String text, LocalDate commentedOn, User by, CategoryScore categoryScore) {
        this.text = text;
        this.commentedOn = commentedOn;
        this.by = by;
        this.categoryScore = categoryScore;
    }
}
