package unq.po2.tpFinal.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import unq.po2.tpFinal.interfaces.BookingAcceptanceStrategy;
import unq.po2.tpFinal.interfaces.Rankeable;
import unq.po2.tpFinal.interfaces.Ranker;

public class Owner extends User implements Rankeable, Ranker {
	private List<Ranking> rankings;	
	private List<Housing> housings;
	
	public Owner(String fullName, String email, String phoneNumber, LocalDateTime createdOn) {
		super(fullName, email, phoneNumber, createdOn);		
		rankings = new ArrayList<Ranking>();
		housings = new ArrayList<Housing>();
	}	

	@Override
	public void addRanking(Ranking ranking) {
		this.rankings.add(ranking);
	}

	@Override
	public List<Ranking> getRankings() {
		return this.rankings;
	}

	@Override
	public void rank(Ranking ranking) {
		ranking.getRanked().addRanking(ranking);
	}

	public void approve(Booking booking) {
		booking.confirm();
	}
	
	public List<Housing> getHousings(){
		return this.housings;
	}
}