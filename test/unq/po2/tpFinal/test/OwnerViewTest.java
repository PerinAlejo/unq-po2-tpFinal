package unq.po2.tpFinal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unq.po2.tpFinal.domain.CategoryScore;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.views.OwnerRentalView;
import unq.po2.tpFinal.views.OwnerView;

public class OwnerViewTest {

	@Mock
	private Owner mockOwner;

	@Mock
	private Housing mockHousing;

	@InjectMocks
	private OwnerView ownerView;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		ownerView = new OwnerView(mockOwner, mockHousing);
	}

	@Test
	public void testGetAllScores() {

		CategoryScore score1 = mock(CategoryScore.class);
		CategoryScore score2 = mock(CategoryScore.class);
		when(score1.getScore()).thenReturn(85);
		when(score2.getScore()).thenReturn(90);

		Ranking ranking = mock(Ranking.class);
		when(ranking.getScores()).thenReturn(Arrays.asList(score1, score2));

		when(mockOwner.getRankings()).thenReturn(List.of(ranking));

		List<Integer> scores = ownerView.getAllScores();

		assertEquals(List.of(85, 90), scores);
	}

	@Test
	public void testGetAverageScore() {

		CategoryScore score1 = mock(CategoryScore.class);
		CategoryScore score2 = mock(CategoryScore.class);
		when(score1.getScore()).thenReturn(80);
		when(score2.getScore()).thenReturn(90);

		Ranking ranking = mock(Ranking.class);
		when(ranking.getScores()).thenReturn(Arrays.asList(score1, score2));

		when(mockOwner.getRankings()).thenReturn(List.of(ranking));

		double averageScore = ownerView.getAverageScore();

		assertEquals(85.0, averageScore, 0.01);
	}

	@Test
	public void testGetCreatedOn() {
		LocalDateTime now = LocalDateTime.now();
		when(mockOwner.getCreatedOn()).thenReturn(now);

		LocalDateTime createdOn = ownerView.getCreatedOn();

		assertEquals(now, createdOn);
	}

	@Test
	public void testGetOwnerRentalView() {
		OwnerRentalView ownerRentalView = ownerView.getOwnerRentalView();

		assertNotNull(ownerRentalView);
	}
}
