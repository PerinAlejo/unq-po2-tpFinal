package unq.po2.tpFinal.views;

import java.util.List;
import java.util.stream.Collectors;

import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;

public class OwnerRentalView {    
    private Owner owner;
    private Housing housing;

    public OwnerRentalView(Owner owner, Housing currentHousing){
        this.owner = owner;
        this.housing =currentHousing;
    }

    public long getTotalRentsForHousing(){
        return this.owner
            .getRentals()
            .stream()
            .filter(rental -> rental.getHousing().equals(this.housing))
            .count();
    };


    public int getTotalRentsForAllHousings(){
        return this.owner
            .getRentals()
            .size();
    }

    public long getTotalTimesHasRentedHousing(){
        return this.getRentedHousings().size();
    }
    
    public List<Housing> getRentedHousings(){
        return this.owner
            .getRentals()
            .stream()
            // Como hacer para chequear que Owner es Tenant? Guardar como user?
            .filter(rental -> rental.getTenant().equals(this.owner))
            .map(rental -> rental.getHousing())
            .collect(Collectors.toList());
    }
}
