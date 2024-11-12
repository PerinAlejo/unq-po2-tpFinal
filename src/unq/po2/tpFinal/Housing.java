package unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Housing implements Rankeable {
	private HousingType housingType;
	private float area;
	private Address address;
	private List<Service> services;
	private int capacity;
	private List<Picture> pictures;
	private HousingStayDetails stayDetails;
	private List<PaymentMethod> paymentMethods;
	private PriceCalculatorInterface priceCalculator;
	private List<Ranking> rankings;
	private Owner owner;
	private CancellationPolicy cancellationPolicy;
	private List<Booking> bookings = new ArrayList<>();
    private Queue<Booking> waitlist = new LinkedList<>();  // Cola de reservas pendientes
	
	public Housing(HousingType housingType, float area, Address address, List<Service> services, int capacity,
			List<Picture> pictures, HousingStayDetails stayDetails, List<PaymentMethod> paymentMethods,
			PriceCalculatorInterface priceCalculator, Owner owner, CancellationPolicy cancellationPolicy) {	
		this.housingType = housingType;
		this.area = area;
		this.address = address;
		this.services = services;
		this.capacity = capacity;
		this.pictures = pictures;
		this.stayDetails = stayDetails;
		this.paymentMethods = paymentMethods;
		this.priceCalculator = priceCalculator;
		this.owner = owner;
		this.rankings = new ArrayList<Ranking>();
		this.cancellationPolicy = cancellationPolicy;
	}
	
	public double getPrice(DateRange range) {
		return this.priceCalculator.getPrice(range);
	}

	public int getCapacity() {
		return this.capacity;
	}

	public Address getAddress() {		
		return this.address;
	}

	public boolean isLocatedIn(City city) {
		return this.getCity().equals(city);
	}

	public boolean isAvailable(DateRange dateRange) {
		return true;
	}

    public Owner getOwner() {        
        return this.owner;
    }
    
    public List<Ranking> getRankings(){
    	return this.rankings;
    }

	@Override
	public void addRanking(Ranking ranking) {
		this.rankings.add(ranking);
	}
	
	public City getCity() {
		return this.address.getCity();
	}

	public double getCancelationFee(DateRange range) {
		return this.cancellationPolicy.getCancellationFee(range);
	}
	
	public boolean isOccupied(DateRange range) {
        return bookings.stream().anyMatch(booking -> booking.getRange().overlaps(range));
    }
	
	public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void addToWaitlist(Booking booking) {
        this.waitlist.add(booking);
    }

    public void processWaitlist() {
        //procesar la cola de espera cuando haya disponibilidad
        for (Booking waitlistedBooking : new ArrayList<>(waitlist)) {
            if (!isOccupied(waitlistedBooking.getRange())) {
                bookings.add(waitlistedBooking); //se acepta la reserva
                waitlist.remove(waitlistedBooking); //se elimina de la cola de espera
            }
        }
    }
}
