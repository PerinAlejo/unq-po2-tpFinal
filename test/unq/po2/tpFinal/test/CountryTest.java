package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.po2.tpFinal.domain.Country;

public class CountryTest {

    private Country country;

    @BeforeEach
    public void setUp() {
        country = new Country("Argentina");
    }

    @Test
    public void testGetName() {
        assertEquals("Argentina", country.getName());
    }
}
