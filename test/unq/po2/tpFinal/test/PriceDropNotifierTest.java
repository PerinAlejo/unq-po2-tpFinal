package unq.po2.tpFinal.test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.HousingType;
import unq.po2.tpFinal.implementations.PriceDropNotifier;
import unq.po2.tpFinal.interfaces.HomePagePublisher;

class PriceDropNotifierTest {

    private PriceDropNotifier priceDropNotifier;
    private HomePagePublisher publisherMock;
    private Housing housingMock;

    @BeforeEach
    void setUp() {
        publisherMock = mock(HomePagePublisher.class);
        housingMock = mock(Housing.class);
        priceDropNotifier = new PriceDropNotifier(publisherMock);
    }

    @Test
    void testNotifyPriceDrop() {
        double newPrice = 1000.0;
        HousingType housingTypeMock = mock(HousingType.class);
        when(housingTypeMock.getName()).thenReturn("departamento");
        when(housingMock.getHousingType()).thenReturn(housingTypeMock);

        priceDropNotifier.notifyPriceDrop(housingMock, newPrice);

        verify(publisherMock).publish("No te pierdas esta oferta: Un inmueble departamento a tan sólo 1000.0 pesos.");
    }
}
