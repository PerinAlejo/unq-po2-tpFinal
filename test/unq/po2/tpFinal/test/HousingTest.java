package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.*;

public class HousingTest {
	private Housing housing;

    @BeforeEach
    public void setUp() {
        HousingType housingType = HousingType.Apartment;
        float area = 75.0f;
        Address address = new Address(new Country("Argentina"), new City("Quilmes"), "Quilmes Oeste");
        List<Service> services = new ArrayList<>();
        int capacity = 4;
        List<Picture> pictures = new ArrayList<>();
        HousingStayDetails stayDetails = new HousingStayDetails(LocalDateTime.of(2023, 1, 1, 0, 0), LocalDateTime.of(2023, 1, 10, 23, 59));
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        DateRange range1 = new DateRange(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10));
        DateRange range2 = new DateRange(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 5));
        DateRange range3 = new DateRange(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 15));
        PriceForRange priceForRange1 = new PriceForRange(100.0, range1);
        PriceForRange priceForRange2 = new PriceForRange(150.0, range2);
        PriceForRange priceForRange3 = new PriceForRange(200.0, range3);
        List<PriceForRange> priceForRangeList = new ArrayList<>();
        priceForRangeList.add(priceForRange1);
        priceForRangeList.add(priceForRange2);
        priceForRangeList.add(priceForRange3);
        PriceCalculatorInterface priceCalculator = new PriceCalculatorImpl(priceForRangeList);

        housing = new Housing(housingType, area, address, services, capacity, pictures, stayDetails, paymentMethods, priceCalculator, null);
    }

    @Test
    public void testAddRating() {
        housing.addRating(4);
        assertEquals(4, housing.getAverageRating());

        housing.addRating(5);
        assertEquals(4.5, housing.getAverageRating());
    }

    @Test
    public void testGetAverageRatingWithNoRatings() {
        assertEquals(0, housing.getAverageRating());
    }
}
