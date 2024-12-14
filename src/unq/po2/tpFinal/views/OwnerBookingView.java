package unq.po2.tpFinal.views;

import java.util.List;
import java.util.stream.Collectors;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;

public class OwnerBookingView {
	private Owner owner;
	private Housing housing;

	public OwnerBookingView(Owner owner, Housing currentHousing) {
		this.owner = owner;
		this.housing = currentHousing;
	}

	public long getTotalBookingsForHousing() {
		return this.housing.getBookings().size();
	};

	public long getTotalBookingsForAllHousings() {		
		return this.owner
				.getHousings().stream()
				.flatMap(housing -> housing.getBookings().stream())
				.count();				
	}

	public long getTotalTimesCurrentHousingWasBooked() {
		return this.getBookingsForCurrentHousing().size();
	}

	public List<Booking> getBookingsForCurrentHousing() {
		return this.housing.getBookings();
	}
}
