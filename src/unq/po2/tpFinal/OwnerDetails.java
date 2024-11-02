package unq.po2.tpFinal;

import java.time.LocalDateTime;
import java.util.List;

// A su vez, para un inmueble, el usuario podrá visualizar también la
// información propia del dueño, el puntaje que otros usuarios le han dado a él mismo
// y el puntaje promedio que ha obtenido. Entre la información del dueño el sistema
// incorpora aquella relativa a su operatoria con el sitio: cuánto hace que es usuario,
// cuántas veces ha alquilado ese inmueble, cuántas veces ha alquilado inmuebles
// (más a allá del seleccionado en particular) y cuáles han sido.

public class OwnerDetails {
    private Owner owner;
    private Housing currentHousing;

    public OwnerDetails(Owner owner, Housing currentHousing) {
        this.owner = owner;
        this.currentHousing = currentHousing;
    }

    public List<Integer> getAllScores(){
        //this.owner.getRankings().map(ranking -> ranking.getScore());
        return null;
    }

    public double averageScore(){
        return getAllScores()
            .stream()
            .mapToInt(score -> score)
            .average()
            .getAsDouble();
    }


    public LocalDateTime createdOn(){
        return this.owner.getCreatedOn();
    };

    public OwnerRentalStats ownerRentalStats(){
        return new OwnerRentalStats(owner, currentHousing);
    }


}
