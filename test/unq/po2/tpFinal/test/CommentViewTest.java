package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.CategoryScore;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.interfaces.Ranker;
import unq.po2.tpFinal.views.CommentView;

public class CommentViewTest {

	private Ranking mockRanking;
	private Ranker mockRanker;
	private CategoryScore score1;
	private CategoryScore score2;

	@BeforeEach
	public void setUp() {
		mockRanking = mock(Ranking.class);
		mockRanker = mock(Ranker.class);

		score1 = mock(CategoryScore.class);
		score2 = mock(CategoryScore.class);
	}

	@Test
	public void testFromRanking() {

		String commentText = "No me gusto, algo sucio. En la esquina se junta basura";
		LocalDate rankedOnDate = LocalDate.of(2023, 10, 15);
		List<CategoryScore> scores = Arrays.asList(score1, score2);

		when(mockRanking.getComment()).thenReturn(commentText);
		when(mockRanking.getRankedOn()).thenReturn(rankedOnDate);
		when(mockRanking.getRanker()).thenReturn(mockRanker);
		when(mockRanking.getScores()).thenReturn(scores);

		CommentView commentView = CommentView.fromRanking(mockRanking);

		assertEquals(commentText, commentView.getText());
		assertEquals(rankedOnDate, commentView.getCommentedOn());
		assertEquals(mockRanker, commentView.getBy());
		assertEquals(scores, commentView.getScores());
	}
}
