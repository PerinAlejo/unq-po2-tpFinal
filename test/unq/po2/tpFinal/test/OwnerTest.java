package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.interfaces.BookingAcceptanceStrategy;
import java.time.LocalDateTime;
import java.util.List;

public class OwnerTest {

	private Owner owner;
	private Booking mockBooking;	

	@BeforeEach
	public void setUp() {
		mockBooking = mock(Booking.class);
		owner = new Owner("Juanita Perez", "perez@perez.com", "0987654321", LocalDateTime.now());
	}

	@Test
	public void testAddRanking() {
		Ranking mockRanking = mock(Ranking.class);
		owner.addRanking(mockRanking);

		List<Ranking> rankings = owner.getRankings();
		assertEquals(1, rankings.size());
		assertTrue(rankings.contains(mockRanking));
	}

	@Test
	public void testGetRankings() {
		Ranking mockRanking = mock(Ranking.class);
		owner.addRanking(mockRanking);

		List<Ranking> rankings = owner.getRankings();
		assertEquals(1, rankings.size());
		assertEquals(mockRanking, rankings.get(0));
	}

	@Test
	public void testRank() {
		Ranking mockRanking = mock(Ranking.class);
		when(mockRanking.getRanked()).thenReturn(owner);

		owner.rank(mockRanking);

		List<Ranking> rankings = owner.getRankings();
		assertEquals(1, rankings.size());
		assertEquals(mockRanking, rankings.get(0));
	}

	@Test
	public void testAcceptBooking() {
		Housing mockHousing = mock(Housing.class);
		when(mockBooking.getHousing()).thenReturn(mockHousing);
		verify(mockHousing, never()).book(mockBooking);
	}

	@Test
	public void testAcceptBookingNotAccepted() {
		Housing mockHousing = mock(Housing.class);
		when(mockBooking.getHousing()).thenReturn(mockHousing);
		owner.approve(mockBooking);
		verify(mockHousing, never()).book(mockBooking);
	}
}