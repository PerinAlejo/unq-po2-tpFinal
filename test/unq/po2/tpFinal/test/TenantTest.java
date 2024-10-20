package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import unq.po2.tpFinal.*;

public class TenantTest {
	private Tenant tenant;
	private Housing mockHousing;

    @BeforeEach
    public void setUp() {
    	mockHousing = mock(Housing.class);
    	tenant = new Tenant("Mr. Pruebbas", "prueba@hotmail.com", "1133445566");
    }

    @Test
    public void testAddRatingToUser() {
    	tenant.addRating(5);
    	tenant.addRating(3);
        assertEquals(4.0, tenant.getAverageRating());
    }

    @Test
    public void testGetAverageRatingWithNoRatings() {
        assertEquals(0, tenant.getAverageRating());
    }
    
    @Test
    public void testRankMethodWithMockHousing() {
        //defino el comportamiento del mock para cuando se llame a addRating y getAverageRating
        doNothing().when(mockHousing).addRating(anyInt());
        when(mockHousing.getAverageRating()).thenReturn(4.0);

        //el Tenant usa el metodo rank para calificar el Housing mockeado
        tenant.rank(mockHousing, 5);
        tenant.rank(mockHousing, 3);

        //verifico que el metodo addRating se haya llamado dos veces en el mock de Housing con los valores correctos
        verify(mockHousing, times(1)).addRating(5);
        verify(mockHousing, times(1)).addRating(3);

        //verifico que el promedio de calificaciones del mock de Housing sea el esperado
        assertEquals(4.0, mockHousing.getAverageRating(), 0.01);
    }
}
