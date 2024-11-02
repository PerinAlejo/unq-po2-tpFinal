package unq.po2.tpFinal;

import java.util.Arrays;
import java.util.List;

public class HousingView {
    private Housing housing;

    public HousingView(Housing housing) {
        this.housing = housing;
    }

    public List<Comment> comments(){
        return Arrays.asList();
    }

    public List<CategoryAverage> categoryAverages(){
        return Arrays.asList();
    }

    public OwnerDetails ownerDetails(){
        return new OwnerDetails(housing.getOwner(), housing);
    }
}
