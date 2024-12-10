package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import unq.po2.tpFinal.*;
import unq.po2.tpFinal.domain.Booking;
import unq.po2.tpFinal.domain.City;
import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.domain.Tenant;
import unq.po2.tpFinal.interfaces.HousingObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookingSystemTest {
	@Test
    void testGetAllBookings() {
        Housing housing1 = Mockito.mock(Housing.class);
        Housing housing2 = Mockito.mock(Housing.class);
        Booking booking1 = Mockito.mock(Booking.class);
        Booking booking2 = Mockito.mock(Booking.class);
        when(housing1.getBookings()).thenReturn(List.of(booking1));
        when(housing2.getBookings()).thenReturn(List.of(booking2));
        BookingSystem system = new BookingSystem(List.of(housing1, housing2));
        assertEquals(List.of(booking1, booking2), system.getAllBookings());
    }

    @Test
    void testGetAllBookingsForTenant() {
        Housing housing1 = Mockito.mock(Housing.class);
        Housing housing2 = Mockito.mock(Housing.class);
        Booking booking1 = Mockito.mock(Booking.class);
        Booking booking2 = Mockito.mock(Booking.class);
        Booking booking3 = Mockito.mock(Booking.class);
        Tenant tenant = Mockito.mock(Tenant.class);
        Tenant otherTenant = Mockito.mock(Tenant.class);
        when(housing1.getBookings()).thenReturn(List.of(booking1, booking2));
        when(housing2.getBookings()).thenReturn(List.of(booking3));
        when(booking1.getTenant()).thenReturn(tenant);
        when(booking2.getTenant()).thenReturn(otherTenant);
        when(booking3.getTenant()).thenReturn(tenant);
        BookingSystem system = new BookingSystem(List.of(housing1, housing2));
        assertEquals(List.of(booking1, booking3), system.getAllBookings(tenant));
    }

    @Test
    void testGetFutureBookings() {
        Housing housing1 = Mockito.mock(Housing.class);
        Booking booking1 = Mockito.mock(Booking.class);
        Booking booking2 = Mockito.mock(Booking.class);
        Tenant tenant = Mockito.mock(Tenant.class);
        when(housing1.getBookings()).thenReturn(List.of(booking1, booking2));
        when(booking1.getTenant()).thenReturn(tenant);
        when(booking2.getTenant()).thenReturn(tenant);
        when(booking1.startsAfter(LocalDate.now())).thenReturn(true);
        when(booking2.startsAfter(LocalDate.now())).thenReturn(false);
        BookingSystem system = new BookingSystem(List.of(housing1));
        assertEquals(List.of(booking1), system.getFutureBookings(tenant));
    }

    @Test
    void testGetBookingsFromCity() {
        Housing housing1 = Mockito.mock(Housing.class);
        Booking booking1 = Mockito.mock(Booking.class);
        Booking booking2 = Mockito.mock(Booking.class);
        Tenant tenant = Mockito.mock(Tenant.class);
        City city = Mockito.mock(City.class);
        when(housing1.getBookings()).thenReturn(List.of(booking1, booking2));
        when(booking1.getTenant()).thenReturn(tenant);
        when(booking2.getTenant()).thenReturn(tenant);
        when(booking1.isOnCity(city)).thenReturn(true);
        when(booking2.isOnCity(city)).thenReturn(false);
        BookingSystem system = new BookingSystem(List.of(housing1));
        assertEquals(List.of(booking1), system.getBookingsFromCity(tenant, city));
    }

    @Test
    void testGetBookingCities() {
        Housing housing1 = Mockito.mock(Housing.class);
        Booking booking1 = Mockito.mock(Booking.class);
        Booking booking2 = Mockito.mock(Booking.class);
        Tenant tenant = Mockito.mock(Tenant.class);
        City city = Mockito.mock(City.class);
        when(housing1.getBookings()).thenReturn(List.of(booking1, booking2));
        when(booking1.getTenant()).thenReturn(tenant);
        when(booking2.getTenant()).thenReturn(tenant);
        when(booking1.getCity()).thenReturn(city);
        when(booking2.getCity()).thenReturn(city);
        BookingSystem system = new BookingSystem(List.of(housing1));
        assertEquals(List.of(city, city), system.getBookingCities(tenant));
    }

}