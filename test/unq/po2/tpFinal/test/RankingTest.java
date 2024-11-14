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
import unq.po2.tpFinal.interfaces.Rankeable;
import unq.po2.tpFinal.interfaces.Ranker;

public class RankingTest {

    private Ranking ranking;
    private Ranker mockRanker;
    private Rankeable mockRankeable;
    private List<CategoryScore> categoryScores;
    private CategoryScore mockScore1;
    private CategoryScore mockScore2;

    @BeforeEach
    public void setUp() {
        mockRanker = mock(Ranker.class);
        mockRankeable = mock(Rankeable.class);
        mockScore1 = mock(CategoryScore.class);
        mockScore2 = mock(CategoryScore.class);


        categoryScores = Arrays.asList(mockScore1, mockScore2);


        ranking = new Ranking(mockRanker, mockRankeable, "Excellent service", categoryScores);
    }

    @Test
    public void testGetScores() {
        assertEquals(categoryScores, ranking.getScores());
    }

    @Test
    public void testGetComment() {
        assertEquals("Excellent service", ranking.getComment());
    }

    @Test
    public void testGetRanker() {
        assertEquals(mockRanker, ranking.getRanker());
    }

    @Test
    public void testGetRanked() {
        assertEquals(mockRankeable, ranking.getRanked());
    }

    @Test
    public void testGetRankedOn() {
        assertEquals(LocalDate.now(), ranking.getRankedOn());
    }
}
