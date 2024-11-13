package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.Category;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.domain.Service;
import unq.po2.tpFinal.domain.Tenant;

import java.time.LocalDate;
import java.util.*;

public class AdminTest {

    private Admin admin;
    private Categories mockCategories;
    private HousingTypes mockHousingTypes;
    private Services mockServices;
    private BookingSystem mockBookingSystem;
    private Set<Housing> mockHousings;

    @BeforeEach
    public void setUp() {
        // Crear mocks
        mockCategories = mock(Categories.class);
        mockHousingTypes = mock(HousingTypes.class);
        mockServices = mock(Services.class);
        mockBookingSystem = mock(BookingSystem.class);
        mockHousings = new HashSet<>();
        
        // Inicializar la clase Admin
        admin = new Admin(mockCategories, mockHousingTypes, mockServices, mockBookingSystem, mockHousings);
    }

    @Test
    public void testAddCategory() {
        Category mockCategory = mock(Category.class);
        admin.addCategory(mockCategory);
        verify(mockCategories).add(mockCategory);
    }

    @Test
    public void testAddHousingType() {
        HousingType mockHousingType = mock(HousingType.class);
        admin.addHousingType(mockHousingType);
        verify(mockHousingTypes).add(mockHousingType);
    }

    @Test
    public void testAddService() {
        Service mockService = mock(Service.class);
        admin.addService(mockService);
        verify(mockServices).add(mockService);
    }

    @Test
    public void testGetServices() {
        List<Service> mockServiceList = new ArrayList<>();
        when(mockServices.getAll()).thenReturn(mockServiceList);
        
        List<Service> services = admin.getServices();
        
        assertEquals(mockServiceList, services);
        verify(mockServices).getAll();
    }

    @Test
    public void testGetTopTenTenants() {
        Tenant mockTenant1 = mock(Tenant.class);
        Tenant mockTenant2 = mock(Tenant.class);
        Booking mockBooking1 = mock(Booking.class);
        Booking mockBooking2 = mock(Booking.class);
        List<Booking> mockBookings = Arrays.asList(mockBooking1, mockBooking2);

        when(mockBookingSystem.getAllBookings()).thenReturn(mockBookings);
        when(mockBooking1.getTenant()).thenReturn(mockTenant1);
        when(mockBooking2.getTenant()).thenReturn(mockTenant1); // Simulando que el mockTenant1 tiene 2 bookings

        List<Tenant> topTenants = admin.getTopTenTenants();

        assertEquals(1, topTenants.size());
        assertTrue(topTenants.contains(mockTenant1));
        verify(mockBookingSystem).getAllBookings();
    }

    @Test
    public void testGetFreeHousings() {
        Housing mockHousing = mock(Housing.class);
        mockHousings.add(mockHousing);
        
        // Simular el comportamiento de busyHousings
        when(mockBookingSystem.getAllBookings()).thenReturn(Collections.emptyList());
        
        Set<Housing> freeHousings = admin.getFreeHousings();
        
        assertEquals(mockHousings, freeHousings);
    }

    @Test
    public void testOccupationRate() {
        Housing mockHousing1 = mock(Housing.class);
        Housing mockHousing2 = mock(Housing.class);
        admin.addHousing(mockHousing1);
        admin.addHousing(mockHousing2);
        
        // Simular el comportamiento de busyHousings
        when(mockBookingSystem.getAllBookings()).thenReturn(Collections.emptyList());
        
        double rate = admin.occupationRate();
        
        assertEquals(0.0, rate);
        
        // Ahora simulamos que uno de los housings está ocupado
        Booking mockBooking = mock(Booking.class);
        when(mockBooking.getHousing()).thenReturn(mockHousing1);
        when(mockBooking.isBookedOn(LocalDate.now())).thenReturn(true);
        when(mockBookingSystem.getAllBookings()).thenReturn(Collections.singletonList(mockBooking));
        
        rate = admin.occupationRate();
        
        assertEquals(0.5, rate, 0.01); // 1 ocupado de 2 totales
    }
}