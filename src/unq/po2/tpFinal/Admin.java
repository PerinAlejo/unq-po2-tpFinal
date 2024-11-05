package unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Admin {
//	Dar de alta las categorías que se utilizan en los rankeos para las
//	distintas entidades del sistema: propietario, inquilino e inmueble.	
//	● Dar de alta las distintas categorías que se utilizarán en el rankeo de
//	cada una de las siguientes entidades: dueños, inquilinos e inmuebles.
	private Categories categories;
	private HousingTypes housingTypes;
	private Services services;
	private BookingSystem bookingSystem;
	private Set<Housing> housings;
	
	
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
//	● Dar de alta los Tipos de Inmuebles que se utilizan en el sistema.
	public void addHousingType(HousingType housing) {
		this.housingTypes.add(housing);
	}
	
	
//	● Servicios que pueden tener los inmuebles (gas, wi-fi, etc) que serán
//	seleccionadas por el propietario que posteriormente realice el alta de
//	un inmueble.
	public void addService(Service service) {
		this.services.add(service);
	}
	
	public List<Service> getServices(){
		return this.services.getAll();
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
		return housings.size() / this.busyHousings().size();
	}
	
	private Set<Housing> busyHousings(){
		return this.bookingSystem
				.getAllBookings()
				.stream()
				.filter(booking -> booking.isBookedOn(LocalDate.now()))
				.map(booking -> booking.getHousing())
				.collect(Collectors.toSet());
	}
	
//	● Obtener listados de gestión, como por ejemplo, el top-ten de los
//	inquilinos que más han alquilado, o información como todos los	
//	inmuebles libres, o la tasa de ocupación del sitio (inmuebles alquilados
//	sobre total de inmuebles).
	
}