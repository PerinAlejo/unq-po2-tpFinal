package unq.po2.tpFinal;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerRentalStats {    
    private Owner owner;
    private Housing housing;

    public OwnerRentalStats(Owner owner, Housing currentHousing){
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
            .filter(rental -> rental.getTenant().equals(this.owner))
            .map(rental -> rental.getHousing())
            .collect(Collectors.toList());
    }
}
