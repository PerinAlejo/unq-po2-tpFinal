package unq.po2.tpFinal.domain;

import java.time.LocalDateTime;

public class HousingStayDetails {
	public LocalDateTime checkIn;
	public LocalDateTime checkOut;
	
	public HousingStayDetails(LocalDateTime checkIn, LocalDateTime checkOut) {		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public LocalDateTime getCheckIn() {
		return checkIn;
	}

	public LocalDateTime getCheckOut() {
		return checkOut;
	}	
}
