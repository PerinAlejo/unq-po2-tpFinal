package unq.po2.tpFinal;

public class Rental {
    private Housing housing;

    private DateRange range;
    private Tenant tenant;
    private Owner owner;
    
    public Rental(unq.po2.tpFinal.Housing housing, DateRange range, Tenant tenant, Owner owner) {
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
}
