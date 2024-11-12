package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OwnerTest {

    private Owner owner;
    private Booking mockBooking;
    private BookingAcceptanceStrategy mockAcceptanceStrategy;
    private List<BookingAcceptedObserver> mockObservers;

    @BeforeEach
    public void setUp() {
        // Inicializar mocks
        mockAcceptanceStrategy = mock(BookingAcceptanceStrategy.class);
        mockObservers = new ArrayList<>();
        mockBooking = mock(Booking.class);
        
        // Crear una instancia de Owner
        owner = new Owner("Jane Doe", "jane@example.com", "0987654321", LocalDateTime.now(), mockAcceptanceStrategy, mockObservers);
    }

    @Test
    public void testAddRental() {
        owner.addRental(mockBooking);
        List<Booking> rentals = owner.getRentals();
        
        assertEquals(1, rentals.size());
        assertTrue(rentals.contains(mockBooking));
    }

    @Test
    public void testGetRentals() {
        owner.addRental(mockBooking);
        List<Booking> rentals = owner.getRentals();
        
        assertEquals(1, rentals.size());
        assertEquals(mockBooking, rentals.get(0));
    }

    @Test
    public void testAddRanking() {
        Ranking mockRanking = mock(Ranking.class);
        owner.addRanking(mockRanking);
        
        List<Ranking> rankings = owner.getRankings();
        assertEquals(1, rankings.size());
        assertTrue(rankings.contains(mockRanking));
    }

    @Test
    public void testGetRankings() {
        Ranking mockRanking = mock(Ranking.class);
        owner.addRanking(mockRanking);
        
        List<Ranking> rankings = owner.getRankings();
        assertEquals(1, rankings.size());
        assertEquals(mockRanking, rankings.get(0));
    }

    @Test
    public void testRank() {
        Ranking mockRanking = mock(Ranking.class);
        when(mockRanking.getRanked()).thenReturn(owner);
        
        owner.rank(mockRanking);
        
        List<Ranking> rankings = owner.getRankings();
        assertEquals(1, rankings.size());
        assertEquals(mockRanking, rankings.get(0));
    }

    @Test
    public void testAcceptBooking() {
        // Simular el comportamiento de la estrategia de aceptación
        when(mockAcceptanceStrategy.isAcceptable(mockBooking)).thenReturn(true);
        
        // Crear un mock para el observer
        BookingAcceptedObserver mockObserver = mock(BookingAcceptedObserver.class);
        mockObservers.add(mockObserver);
        
        // Llamar al método accept
        owner.accept(mockBooking);
        
        // Verificar que el método notifyBookingAccepted fue llamado
        verify(mockObserver).notifyBookingAccepted(mockBooking);
    }

    @Test
    public void testAcceptBookingNotAccepted() {
        // Simular el comportamiento de la estrategia de aceptación
        when(mockAcceptanceStrategy.isAcceptable(mockBooking)).thenReturn(false);
        
        // Crear un mock para el observer
        BookingAcceptedObserver mockObserver = mock(BookingAcceptedObserver.class);
        mockObservers.add(mockObserver);
        
        // Llamar al método accept
        owner.accept(mockBooking);
        
        // Verificar que el método notifyBookingAccepted NO fue llamado
        verify(mockObserver, never()).notifyBookingAccepted(mockBooking);
    }
}