package unq.po2.tpFinal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Owner extends User {

	private List<Rental> rentals;

	public Owner(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super(fullName, email, phoneNumber, createdOn); 
		rentals = new ArrayList<Rental>();
	}

	public void addRental(Rental rental){
		this.rentals.add(rental);
	}
	
	public List<Rental> getRentals(){
		return this.rentals;
	}
}
