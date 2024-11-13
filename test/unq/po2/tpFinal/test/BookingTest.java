package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.domain.PaymentMethod;
import unq.po2.tpFinal.domain.Ranker;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingTest {

    private Booking booking;
    private Housing mockHousing;
    private Tenant mockTenant;
    private DateRange mockDateRange;
    private PaymentMethod PaymentMethod;
    private City mockCity;
    private Owner mockOwner;

    @BeforeEach
    public void setUp() {
        // Crear mocks
        mockHousing = mock(Housing.class);
        mockTenant = mock(Tenant.class);
        mockDateRange = mock(DateRange.class);
        PaymentMethod = PaymentMethod.Cash;
        mockCity = mock(City.class);
        mockOwner = mock(Owner.class);

        // Configurar el comportamiento de los mocks
        when(mockHousing.getOwner()).thenReturn(mockOwner);
        when(mockHousing.getCity()).thenReturn(mockCity);
        
        // Inicializar la clase Booking
        booking = new Booking(mockHousing, mockTenant, mockDateRange, PaymentMethod);
    }

    @Test
    public void testGetTenant() {
        assertEquals(mockTenant, booking.getTenant());
    }

    @Test
    public void testGetHousing() {
        assertEquals(mockHousing, booking.getHousing());
    }

    @Test
    public void testGetOwner() {
        User owner = booking.getOwner();
        assertNotNull(owner);
        verify(mockHousing).getOwner();
    }

    @Test
    public void testCheckOut() {
        List<Ranking> rankings = new ArrayList<>();
        Ranking mockRanking = mock(Ranking.class);
        rankings.add(mockRanking);
        
        // Simular el comportamiento de ranking
        when(mockRanking.getRanker()).thenReturn(mock(Ranker.class));
        
        booking.checkOut(rankings);
        
        // Verificar que se haya llamado al método rank
        verify(mockRanking.getRanker()).rank(mockRanking);
    }

    @Test
    public void testStartsAfter() {
        LocalDate date = LocalDate.now();
        when(mockDateRange.startsAfter(date)).thenReturn(true);
        
        assertTrue(booking.startsAfter(date));
        verify(mockDateRange).startsAfter(date);
    }

    @Test
    public void testIsOnCity() {
        when(mockHousing.isLocatedIn(mockCity)).thenReturn(true);
        
        assertTrue(booking.isOnCity(mockCity));
        verify(mockHousing).isLocatedIn(mockCity);
    }

    @Test
    public void testGetCity() {
        assertEquals(mockCity, booking.getCity());
        verify(mockHousing).getCity();
    }

    @Test
    public void testIsBookedOn() {
        LocalDate date = LocalDate.now();
        when(mockDateRange.contains(date)).thenReturn(true);
        
        assertTrue(booking.isBookedOn(date));
        verify(mockDateRange).contains(date);
    }
}