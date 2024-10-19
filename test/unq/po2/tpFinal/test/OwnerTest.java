package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.Owner;

public class OwnerTest {
	private Owner owner;

    @BeforeEach
    public void setUp() {
    	owner = new Owner("Mr. Pruebbas", "prueba@hotmail.com", "1133445566");
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
}
