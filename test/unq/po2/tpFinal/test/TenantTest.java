package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import unq.po2.tpFinal.Housing;
import unq.po2.tpFinal.Tenant;
import org.mockito.Mockito;

public class TenantTest {
	private Tenant tenant;
	private Housing mockHousing;

    @BeforeEach
    public void setUp() {
    	mockHousing = Mockito.mock(Housing.class);
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
}
