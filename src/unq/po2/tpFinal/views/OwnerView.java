package unq.po2.tpFinal.views;

import java.time.LocalDateTime;
import java.util.List;

import unq.po2.tpFinal.Housing;
import unq.po2.tpFinal.Owner;

public class OwnerView {
    private Owner owner;
    private Housing currentHousing;

    public OwnerView(Owner owner, Housing currentHousing) {
        this.owner = owner;
        this.currentHousing = currentHousing;
    }

    public List<Integer> getAllScores(){
        return this.owner.getRankings()
        		.stream()
        		.flatMap(ranking -> ranking.getScores().stream().map(score -> score.getScore()))
        		.toList();
    }

    public double getAverageScore(){
        return getAllScores()
            .stream()
            .mapToInt(score -> score)
            .average()
            .getAsDouble();
    }

    public LocalDateTime getCreatedOn(){
        return this.owner.getCreatedOn();
    }

    public OwnerRentalView getOwnerRentalView(){
        return new OwnerRentalView(owner, currentHousing);
    }
}
