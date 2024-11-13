package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.Category;
import unq.po2.tpFinal.views.CategoryAverageView;

public class CategoryAverageViewTest {

	private Category mockCategory;
	private CategoryAverageView categoryAverage;

	@BeforeEach
	public void setUp() {
		mockCategory = mock(Category.class);
	}

	@Test
	public void testCategoryAverageInitialization() {
		double averageScore = 4.5;

		categoryAverage = new CategoryAverageView(averageScore, mockCategory);

		assertEquals(averageScore, categoryAverage.getAverage());
		assertEquals(mockCategory, categoryAverage.getCategory());
	}
}
