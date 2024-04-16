package ru.fastdelivery.domain.common.route;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

class RouteTest {

  @ParameterizedTest(name = "Проверка процедуры расчета расстояния по формуле гаверсинусов с модификацией")
  @CsvSource({"77.1539, 120.398, 77.1804, 129.55, 226", "77.1539, -139.398, -77.1804, -139.55, 17166",
              "77.1539, -120.398, 77.1804, 129.55, 2333"})
  void testDistance(double lat1, double lon1, double lat2, double lon2, int dist) {

    GeoPointPropertiesProvider provider = Mockito.mock(GeoPointPropertiesProvider.class);
    when(provider.outOfLatitudeLimits(lat1)).thenReturn(false);
    when(provider.outOfLongitudeLimits(lon1)).thenReturn(false);
    when(provider.outOfLatitudeLimits(lat2)).thenReturn(false);
    when(provider.outOfLongitudeLimits(lon2)).thenReturn(false);
    GeoPointFactory factory = new GeoPointFactory(provider);

    GeoPoint departure = factory.create(lat1, lon1);
    GeoPoint destination = factory.create(lat2, lon2);
    Route route = new Route(departure, destination);
    int distance = route.distance();
    assertEquals(dist, distance);
  }
}