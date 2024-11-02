package unq.po2.tpFinal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Owner extends User implements Rankeable, Ranker {
	private List<Ranking> rankings;
	private List<Rental> rentals;

	public Owner(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super(fullName, email, phoneNumber, createdOn); 
		rentals = new ArrayList<Rental>();
		rankings = new ArrayList<Ranking>();
	}

	public void addRental(Rental rental){
		this.rentals.add(rental);
	}
	
	public List<Rental> getRentals(){
		return this.rentals;
	}


	@Override
	public void addRanking(Ranking ranking) {
		this.rankings.add(ranking);
	}
 
	@Override
	public List<Ranking> getRankings(){
    	return this.rankings;
    }

	@Override
	public void rank(Ranking ranking) {
		ranking.getRanked().addRanking(ranking);
	}
}
