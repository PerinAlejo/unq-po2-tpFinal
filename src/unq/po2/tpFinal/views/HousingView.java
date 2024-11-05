package unq.po2.tpFinal.views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import unq.po2.tpFinal.CategoryScore;
import unq.po2.tpFinal.Comment;
import unq.po2.tpFinal.Housing;
import unq.po2.tpFinal.Ranking;
//En el listado obtenido a partir de una búsqueda mediante filtros, el potencial
//inquilino podrá seleccionar un inmueble para su visualización, a partir de lo cual el
//sistema le mostrará todos los datos del mismo, incluyendo 
// -- comentarios de otros usuarios que lo han alquilado previamente
// -- el puntaje que éstos le han dado en los distintos aspectos/categorías
// -- el promedio de puntaje total y por categoría.

//A su vez, para un inmueble, el usuario podrá visualizar también la
//información propia del dueño, el puntaje que otros usuarios le han dado a él mismo
//y el puntaje promedio que ha obtenido. Entre la información del dueño el sistema
//incorpora aquella relativa a su operatoria con el sitio: cuánto hace que es usuario,
//cuántas veces ha alquilado ese inmueble, cuántas veces ha alquilado inmuebles
//(más a allá del seleccionado en particular) y cuáles han sido.

public class HousingView {
    private Housing housing;

    public HousingView(Housing housing) {
        this.housing = housing;
    }

    public List<Comment> comments(){
        return this.housing
        		.getRankings()
        		.stream()
        		.map(ranking -> Comment.fromRanking(ranking))
        		.toList();
    }

    public List<CategoryAverage> categoryAverages(){
        return this.housing
        		.getRankings()        		
        		.stream()
        		.flatMap(ranking -> ranking.getScores().stream())
        	    .collect(Collectors.groupingBy(CategoryScore::getCategory))
        		.entrySet()
        		.stream()
        		.map(categoryGroup -> new CategoryAverage(
        				categoryGroup.getValue().stream().mapToDouble(CategoryScore::getScore).sum(),
        				categoryGroup.getKey()))
        		.toList();        		
    }

    public OwnerView ownerDetails(){
        return new OwnerView(housing.getOwner(), housing);
    }
}
