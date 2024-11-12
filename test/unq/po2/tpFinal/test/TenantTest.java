package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;

import java.time.LocalDateTime;
import java.util.List;

public class TenantTest {

    private Tenant tenant;
    private Ranking mockRanking;
    private Housing mockHousing;
    private Owner mockOwner;
    private DateRange mockDateRange;
    private PaymentMethod paymentMethod;
    @BeforeEach
    public void setUp() {
        // Inicializar un Tenant
        tenant = new Tenant("John Doe", "john@example.com", "1234567890", LocalDateTime.now());

        // Crear mocks para las dependencias
        mockRanking = mock(Ranking.class);
        mockHousing = mock(Housing.class);
        mockOwner = mock(Owner.class);
        mockDateRange = mock(DateRange.class);
        paymentMethod = PaymentMethod.Cash;
        
        // Configurar el mockHousing para que devuelva el mockOwner
        when(mockHousing.getOwner()).thenReturn(mockOwner);
    }

    @Test
    public void testAddRanking() {
        tenant.addRanking(mockRanking);
        List<Ranking> rankings = tenant.getRankings();
        
        assertEquals(1, rankings.size());
        assertTrue(rankings.contains(mockRanking));
    }

    @Test
    public void testGetRankings() {
        tenant.addRanking(mockRanking);
        List<Ranking> rankings = tenant.getRankings();
        
        assertEquals(1, rankings.size());
        assertEquals(mockRanking, rankings.get(0));
    }

    @Test
    public void testRank() {
        // Simular el comportamiento del ranking
        when(mockRanking.getRanked()).thenReturn(tenant);
        
        tenant.rank(mockRanking);
        
        // Verificar que el ranking fue agregado
        List<Ranking> rankings = tenant.getRankings();
        assertEquals(1, rankings.size());
        assertEquals(mockRanking, rankings.get(0));
    }

    @Test
    public void testBook() {
        // Crear la reserva directamente en el método book
        Booking mockBooking = new Booking(mockHousing, tenant, mockDateRange, paymentMethod);
        
        // Simular que el owner acepta la reserva
        doNothing().when(mockOwner).accept(mockBooking);
        
        // Llamar al método book
        tenant.book(mockHousing, mockDateRange, paymentMethod);
        
        // Verificar que el owner acepte la reserva
        verify(mockOwner).accept(any(Booking.class));
    }
}