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
import unq.po2.tpFinal.interfaces.HousingObserver;
import unq.po2.tpFinal.interfaces.PaymentMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TenantTest {

    private Tenant tenant;
    private Ranking mockRanking;
    private Housing mockHousing;
    private Owner mockOwner;
    private DateRange mockDateRange;
    private PaymentMethod paymentMethod;
    private List<HousingObserver> observers;
    
    @BeforeEach
    public void setUp() {

        tenant = new Tenant("John Bonachon", "john@bonachon.com", "0303456", LocalDateTime.now());


        mockRanking = mock(Ranking.class);
        mockHousing = mock(Housing.class);
        mockOwner = mock(Owner.class);
        mockDateRange = mock(DateRange.class);
        paymentMethod = mock(PaymentMethod.class);
        observers = new ArrayList<>();

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
    public void testBook() {

        Booking mockBooking = new Booking(mockHousing, tenant, mockDateRange, paymentMethod);

        doNothing().when(mockOwner).accept(mockBooking);

        tenant.book(mockHousing, mockDateRange, paymentMethod);

        verify(mockOwner).accept(any(Booking.class));
    }
}