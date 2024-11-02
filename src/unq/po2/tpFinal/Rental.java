package unq.po2.tpFinal;

import java.util.List;

public class Rental {
    private Housing housing;
    private DateRange range;
    private Tenant tenant;
    private Owner owner;	
    
    public Rental(Housing housing, DateRange range, Tenant tenant, Owner owner) {
        this.housing = housing;
        this.range = range;
        this.tenant = tenant;
        this.owner = owner;
    }

    public Housing getHousing() {
        return this.housing;
    }    

    public User getTenant(){
        return this.tenant;
    }

    public User getOwner(){
        return this.owner;
    } 
    
    public void checkOut(List<Ranking> rankings) {
    	rankings.forEach(ranking -> ranking.getRanker().rank(ranking));
    }
}
