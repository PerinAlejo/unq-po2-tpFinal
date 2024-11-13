package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.po2.tpFinal.domain.HousingType;

public class HousingTypeTest {

    private HousingType housingType;

    @BeforeEach
    public void setUp() {
        housingType = new HousingType("Apartment");
    }

    @Test
    public void testGetName() {
        assertEquals("Apartment", housingType.getName());
    }
}
