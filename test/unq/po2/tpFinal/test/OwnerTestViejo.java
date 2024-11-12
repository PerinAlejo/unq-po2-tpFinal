package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;

public class OwnerTestViejo {
	private Owner owner;
	private Tenant mockTenant;

    @BeforeEach
    public void setUp() {
    	owner = new Owner("Mr. Pruebbas", "prueba@hotmail.com", "1133445566", LocalDateTime.now());
    	mockTenant = mock(Tenant.class);
    }

    @Test
    public void testAddRatingToUser() {
    	owner.addRating(5);
    	owner.addRating(3);
        assertEquals(4.0, owner.getAverageRating());
    }

    @Test
    public void testGetAverageRatingWithNoRatings() {
        assertEquals(0, owner.getAverageRating());
    }
    
    @Test
    public void testRankMethodWithMockTenant() {
        //defino el comportamiento del mock para cuando se llame a addRating y getAverageRating
        doNothing().when(mockTenant).addRating(anyInt());
        when(mockTenant.getAverageRating()).thenReturn(4.0);

        //el propietario usa el metodo rank para calificar el mockeado
        owner.rank(mockTenant, 5);
        owner.rank(mockTenant, 3);

        //verifico que el metodo addRating se haya llamado dos veces al mock con los valores correctos
        verify(mockTenant, times(1)).addRating(5);
        verify(mockTenant, times(1)).addRating(3);

        //verifico que el promedio de calificaciones del mock sea el esperado
        assertEquals(4.0, mockTenant.getAverageRating(), 0.01);
    }
}
