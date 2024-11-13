package unq.po2.tpFinal.views;

import unq.po2.tpFinal.domain.Category;

public class CategoryAverageView {
    private double average;
    private Category category;
    
    public CategoryAverageView(double average, Category category) {
        this.average = average;
        this.category = category;
    }

	public double getAverage() {
		return average;
	}

	public Category getCategory() {
		return category;
	}    
    
}
