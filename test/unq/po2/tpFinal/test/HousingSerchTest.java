package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;

class HousingSerchTest {

	Housing housing1;
	Housing housing2;
	Housing housing3;
	
	City city;
	DateRange date;


	@BeforeEach
	void setUp() throws Exception {
		
		// Inicializa los mocks
		housing1 = mock(Housing.class);
		housing2 = mock(Housing.class);
		housing3 = mock(Housing.class);
		
		// Preparación de los mocks
        city = new City("New York");
        date = new DateRange(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 1));
		
	}

	@Test
    public void testCityAndDateFilter() {

		// Mockear los parametros de las casas
        when(housing1.isLocatedIn(city)).thenReturn(true);
        when(housing2.isLocatedIn(city)).thenReturn(false);
        when(housing3.isLocatedIn(city)).thenReturn(true);
        
        when(housing1.isAvailable(date)).thenReturn(true);
        when(housing2.isAvailable(date)).thenReturn(true);
        when(housing3.isAvailable(date)).thenReturn(false);
		
        // Crear una lista de casas para probar
        List<Housing> housings = Arrays.asList(housing1, housing2, housing3);

        // Crear el filtro y HousingSearch con el filtro de ciudad
        HousingSearchBuilder builder = new HousingSearchBuilder(city, date);
        SearchFilter searchFilter = builder.build();

        // Aplicar el filtro
        List<Housing> result = searchFilter.filter(housings);

        // Verificar que solo las casas en New York están en el resultado
        assertTrue(result.contains(housing1)); 	//Cumple con todo.
        assertFalse(result.contains(housing2)); //Cumple con la fecha, no con la ciudad.
        assertFalse(result.contains(housing3)); //Cumple con la ciudad, no  con la fecha.
    }
	
	  @Test
	    public void testCapacityFilter() {
	        // Mockear los parametros de las casas
	        when(housing1.isLocatedIn(city)).thenReturn(true);
	        when(housing2.isLocatedIn(city)).thenReturn(true);
	        when(housing3.isLocatedIn(city)).thenReturn(true);
	        
	        when(housing1.isAvailable(date)).thenReturn(true);
	        when(housing2.isAvailable(date)).thenReturn(true);
	        when(housing3.isAvailable(date)).thenReturn(true);
		  
	        when(housing1.getCapacity()).thenReturn(5);
	        when(housing2.getCapacity()).thenReturn(2);
	        when(housing3.getCapacity()).thenReturn(6);

	        // Crear una lista de casas para probar
	        List<Housing> housings = Arrays.asList(housing1, housing2, housing3);

	        // Crear el filtro y HousingSearch con el filtro de ciudad
	        HousingSearchBuilder builder = new HousingSearchBuilder(city, date)
	        								   .setCapacity(4);
	        SearchFilter searchFilter = builder.build();

	        // Aplicar el filtro
	        List<Housing> result = searchFilter.filter(housings);

	        // Verificar que solo las casas con capacidad >= 4 están en el resultado
	        assertTrue(result.contains(housing1));	//Cumple con la Capacidad
	        assertFalse(result.contains(housing2));	//No cumple con la Capacidad
	        assertTrue(result.contains(housing3));	//Cumple con la Capacidad
	    }
	  
	  @Test
	    public void testPriceFilter() {
	        // Mockear los parametros de las casas
	        when(housing1.isLocatedIn(city)).thenReturn(true);
	        when(housing2.isLocatedIn(city)).thenReturn(true);
	        when(housing3.isLocatedIn(city)).thenReturn(true);
	        
	        when(housing1.isAvailable(date)).thenReturn(true);
	        when(housing2.isAvailable(date)).thenReturn(true);
	        when(housing3.isAvailable(date)).thenReturn(true);
		  
	        when(housing1.getPrice(date)).thenReturn(1000.0);
	        when(housing2.getPrice(date)).thenReturn(2000.0);
	        when(housing3.getPrice(date)).thenReturn(800.0);

	        // Crear una lista de casas para probar
	        List<Housing> housings = Arrays.asList(housing1, housing2, housing3);

	        // Crear el filtro y HousingSearch con el filtro de ciudad
	        HousingSearchBuilder builder = new HousingSearchBuilder(city, date)
	        								   .setMinPrice(900.0)
	        								   .setMaxPrice(1500.0);
	        SearchFilter searchFilter = builder.build();

	        // Aplicar el filtro
	        List<Housing> result = searchFilter.filter(housings);

	        // Verificar que solo las casas con capacidad >= 4 están en el resultado
	        assertTrue(result.contains(housing1));	//Cumple con el rango de precios
	        assertFalse(result.contains(housing2));	//No cumple con precio Maximo
	        assertFalse(result.contains(housing3));	//No Cumple con precio Minimo
	    }
}