package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.Owner;
import unq.po2.tpFinal.domain.Ranking;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.interfaces.PaymentMethod;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TenantTest {
    // Previous fields from the original test class
    private Tenant tenant;
    private Ranking mockRanking;
    private Housing mockHousing;
    private Owner mockOwner;
    
    // Additional mocks for new tests
    private DateRange mockDateRange;
    private PaymentMethod mockPaymentMethod;
    
    @BeforeEach
    public void setUp() {
        tenant = new Tenant("John Bonachon", "john@bonachon.com", "0303456", LocalDateTime.now());
        mockRanking = mock(Ranking.class);
        mockHousing = mock(Housing.class);
        mockOwner = mock(Owner.class);
        mockDateRange = mock(DateRange.class);
        mockPaymentMethod = mock(PaymentMethod.class);
        
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
        when(mockRanking.getRanked()).thenReturn(tenant);

        tenant.rank(mockRanking);

        List<Ranking> rankings = tenant.getRankings();
        assertEquals(1, rankings.size());
        assertEquals(mockRanking, rankings.get(0));
    }
    
    @Test
    public void testSuccessfulBookingWhenHousingIsAvailable() throws Exception {
        when(mockHousing.isAvailable(mockDateRange)).thenReturn(true);
        
        tenant.book(mockHousing, mockDateRange, mockPaymentMethod);
        
        verify(mockHousing).addPendingBooking(any(Booking.class));
    }
    
    @Test
    public void testBookingThrowsExceptionWhenHousingIsNotAvailable() {
        when(mockHousing.isAvailable(mockDateRange)).thenReturn(false);
        
        Exception exception = assertThrows(Exception.class, () -> {
            tenant.book(mockHousing, mockDateRange, mockPaymentMethod);
        });
        
        assertEquals("La casa está ocupada en el período seleccionada", exception.getMessage());
    }
    
    @Test
    public void testConditionalBookAlwaysAddsPendingBooking() {
        tenant.conditionalBook(mockHousing, mockDateRange, mockPaymentMethod);
        
        verify(mockHousing).addPendingBooking(any(Booking.class));
    }
    
    @Test
    public void testTenantCreationWithCorrectInitialState() {
        LocalDateTime creationTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Tenant newTenant = new Tenant("Jane Doe", "jane@example.com", "1234567", creationTime);
        
        assertEquals("Jane Doe", newTenant.getFullName());
        assertEquals("jane@example.com", newTenant.getEmail());
        assertEquals("1234567", newTenant.getPhoneNumber());
        assertEquals(creationTime, newTenant.getCreatedOn());
        assertTrue(newTenant.getRankings().isEmpty());
    }
}