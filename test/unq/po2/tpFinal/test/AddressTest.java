package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.Address;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.Country;

public class AddressTest {

	private Address address;
	private Country mockCountry;
	private City mockCity;

	@BeforeEach
	public void setUp() {
		mockCountry = mock(Country.class);
		mockCity = mock(City.class);
		address = new Address(mockCountry, mockCity, "123 Main St");
	}

	@Test
	public void testGetHouseLocation() {
		assertEquals("123 Main St", address.getHouseLocation());
	}

	@Test
	public void testGetCity() {
		assertEquals(mockCity, address.getCity());
	}

	@Test
	public void testGetCountry() {
		assertEquals(mockCountry, address.getCountry());
	}
}
