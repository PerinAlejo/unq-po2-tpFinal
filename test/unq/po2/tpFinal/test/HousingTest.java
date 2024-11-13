package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;
import unq.po2.tpFinal.domain.Address;
import unq.po2.tpFinal.domain.CancellationPolicy;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingStayDetails;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.domain.PaymentMethod;
import unq.po2.tpFinal.domain.Picture;
import unq.po2.tpFinal.domain.PriceCalculatorInterface;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.domain.Service;

import java.util.ArrayList;
import java.util.List;

public class HousingTest {

    private Housing housing;
    private HousingType mockHousingType;
    private Address mockAddress;
    private List<Service> mockServices;
    private List<Picture> mockPictures;
    private HousingStayDetails mockStayDetails;
    private List<PaymentMethod> mockPaymentMethods;
    private PriceCalculatorInterface mockPriceCalculator;
    private CancellationPolicy mockCancellationPolicy;
    private Owner mockOwner;

    @BeforeEach
    public void setUp() {
        // Inicializar mocks
        mockHousingType = mock(HousingType.class);
        mockAddress = mock(Address.class);
        mockServices = new ArrayList<>();
        mockPictures = new ArrayList<>();
        mockStayDetails = mock(HousingStayDetails.class);
        mockPaymentMethods = new ArrayList<>();
        mockPriceCalculator = mock(PriceCalculatorInterface.class);
        mockOwner = mock(Owner.class);
        mockCancellationPolicy = mock(CancellationPolicy.class);

        // Crear una instancia de Housing
        housing = new Housing(mockHousingType, 100.0f, mockAddress, mockServices, 4,
                mockPictures, mockStayDetails, mockPaymentMethods, mockPriceCalculator, mockOwner, mockCancellationPolicy);
    }

    @Test
    public void testGetPrice() {
        DateRange mockDateRange = mock(DateRange.class);
        double expectedPrice = 200.0;
        
        // Simular el comportamiento del PriceCalculator
        when(mockPriceCalculator.getPrice(mockDateRange)).thenReturn(expectedPrice);
        
        double price = housing.getPrice(mockDateRange);
        
        assertEquals(expectedPrice, price);
        verify(mockPriceCalculator).getPrice(mockDateRange);
    }

    @Test
    public void testGetCapacity() {
        int capacity = housing.getCapacity();
        assertEquals(4, capacity);
    }

    @Test
    public void testGetAddress() {
        Address address = housing.getAddress();
        assertEquals(mockAddress, address);
    }

    @Test
    public void testIsLocatedIn() {
        City mockCity = mock(City.class);
        when(mockAddress.getCity()).thenReturn(mockCity);
        
        assertTrue(housing.isLocatedIn(mockCity));
    }

    @Test
    public void testIsAvailable() {
        DateRange mockDateRange = mock(DateRange.class);
        assertTrue(housing.isAvailable(mockDateRange)); // Asumiendo que siempre devuelve true
    }

    @Test
    public void testGetOwner() {
        Owner owner = housing.getOwner();
        assertEquals(mockOwner, owner);
    }

    @Test
    public void testAddRanking() {
        Ranking mockRanking = mock(Ranking.class);
        housing.addRanking(mockRanking);
        
        List<Ranking> rankings = housing.getRankings();
        assertEquals(1, rankings.size());
        assertTrue(rankings.contains(mockRanking));
    }

    @Test
    public void testGetCity() {
        City mockCity = mock(City.class);
        when(mockAddress.getCity()).thenReturn(mockCity);
        
        City city = housing.getCity();
        assertEquals(mockCity, city);
    }
}