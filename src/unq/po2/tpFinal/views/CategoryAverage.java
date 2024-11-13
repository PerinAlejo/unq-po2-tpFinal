package unq.po2.tpFinal.views;

import unq.po2.tpFinal.domain.Category;

public class CategoryAverage {
    private double average;
    private Category category;
    
    public CategoryAverage(double average, Category category) {
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
