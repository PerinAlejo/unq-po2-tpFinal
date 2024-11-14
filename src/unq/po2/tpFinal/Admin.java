package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Category;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.interfaces.Service;

public class Admin {
	private Categories categories;
	private HousingTypes housingTypes;
	private Services services;
	private BookingSystem bookingSystem;
	private Set<Housing> housings;
	
	public Admin(Categories categories, HousingTypes housingTypes, Services services, BookingSystem bookingSystem,
			Set<Housing> housings) {
		this.categories = categories;
		this.housingTypes = housingTypes;
		this.services = services;
		this.bookingSystem = bookingSystem;
		this.housings = housings;
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public void addHousingType(HousingType housing) {
		this.housingTypes.add(housing);
	}
	
	public void addService(Service service) {
		this.services.add(service);
	}
	
	public List<Service> getServices(){
		return this.services.getAll();
	}
	
	public void addHousing(Housing housing) {
		this.housings.add(housing);
	}
	
	public List<Tenant> getTopTenTenants(){
		return this.bookingSystem
				.getAllBookings()
				.stream()
				.collect(Collectors.groupingBy(Booking::getTenant))
        		.entrySet()
        		.stream()
        		.sorted(Comparator.comparingInt(entry -> entry.getValue().size()))
        		.limit(10)
        		.map(group -> group.getKey())
        		.toList();
	}
	
	public Set<Housing> getFreeHousings(){
		Set<Housing> freeHousings = new HashSet<Housing>(housings);
		freeHousings.removeAll(this.busyHousings());
		return freeHousings;		
	}
	
	public double occupationRate() {
	    Set<Housing> busyHousings = this.busyHousings();
	    if (housings.isEmpty()) {
	        return 0.0;
	    }
	    return (double) busyHousings.size() / housings.size();
	}
	
	private Set<Housing> busyHousings(){
		return this.bookingSystem
				.getAllBookings()
				.stream()
				.filter(booking -> booking.isBookedOn(LocalDate.now()))
				.map(booking -> booking.getHousing())
				.collect(Collectors.toSet());
	}	
}