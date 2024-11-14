package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import unq.po2.tpFinal.domain.HousingStayDetails;

public class HousingStayDetailsTest {

	private HousingStayDetails housingStayDetails;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;

	@BeforeEach
	public void setUp() {

		checkIn = LocalDateTime.of(2023, 1, 1, 14, 0);
		checkOut = LocalDateTime.of(2023, 1, 10, 11, 0);

		housingStayDetails = new HousingStayDetails(checkIn, checkOut);
	}

	@Test
	public void testGetCheckIn() {

		assertEquals(checkIn, housingStayDetails.getCheckIn());
	}

	@Test
	public void testGetCheckOut() {

		assertEquals(checkOut, housingStayDetails.getCheckOut());
	}
}
