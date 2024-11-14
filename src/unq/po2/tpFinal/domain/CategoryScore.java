package unq.po2.tpFinal.domain;

public class CategoryScore {
    private Category category;
    private int score;
    
    public CategoryScore(Category category, int score) {
        this.category = category;
        this.score = score;
    }    
    
    public Category getCategory() {
		return category;
	}

	public int getScore() {
		return score;
	}

}
