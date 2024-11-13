package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unq.po2.tpFinal.domain.Category;
import unq.po2.tpFinal.domain.CategoryScore;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.views.CategoryAverageView;
import unq.po2.tpFinal.views.CommentView;
import unq.po2.tpFinal.views.HousingView;

public class HousingViewTest {

	@Mock
	private Housing mockHousing;

	@InjectMocks
	private HousingView housingView;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		housingView = new HousingView(mockHousing);
	}

	@Test
	public void testComments() {

		Ranking ranking1 = mock(Ranking.class);
		Ranking ranking2 = mock(Ranking.class);

		when(mockHousing.getRankings()).thenReturn(Arrays.asList(ranking1, ranking2));

		List<CommentView> comments = housingView.comments();

		assertEquals(2, comments.size());
	}

	@Test
	public void testCategoryAverages() {

		Category category1 = new Category("Category1");
		Category category2 = new Category("Category2");

		CategoryScore score1 = new CategoryScore(category1, 4);
		CategoryScore score2 = new CategoryScore(category1, 3);
		CategoryScore score3 = new CategoryScore(category2, 5);

		Ranking ranking1 = mock(Ranking.class);
		when(ranking1.getScores()).thenReturn(Arrays.asList(score1, score2, score3));

		when(mockHousing.getRankings()).thenReturn(List.of(ranking1));

		List<CategoryAverageView> categoryAverages = housingView.categoryAverages();

		assertEquals(2, categoryAverages.size());

		CategoryAverageView categoryAverage1 = categoryAverages.stream()
				.filter(avg -> avg.getCategory().equals(category1)).findFirst().orElse(null);
		assertNotNull(categoryAverage1);
		assertEquals(7.0, categoryAverage1.getAverage());

		CategoryAverageView categoryAverage2 = categoryAverages.stream()
				.filter(avg -> avg.getCategory().equals(category2)).findFirst().orElse(null);
		assertNotNull(categoryAverage2);
		assertEquals(5.0, categoryAverage2.getAverage());
	}

}
