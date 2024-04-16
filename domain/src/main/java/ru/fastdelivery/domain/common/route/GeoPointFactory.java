package ru.fastdelivery.domain.common.route;

import lombok.RequiredArgsConstructor;

/**
 * Создание точки по географическим координатам с проверками исходных данных
 */
@RequiredArgsConstructor
public class GeoPointFactory {

  private final GeoPointPropertiesProvider provider;
  private static final double PI = 3.14159265358979;

  /**
   *
   * @param latitude широта точки в градусах
   * @param longitude долгота точки в градусах
   * @return Экземпляр географической точки с координатами в радианах
   */
  public GeoPoint create (double latitude, double longitude) {
    if (provider.outOfLatitudeLimits(latitude)) {
      throw new IllegalArgumentException("Latitude value is out of limits!");
    }
    if (provider.outOfLongitudeLimits(longitude)) {
      throw new IllegalArgumentException("Longitude value is out of limits!");
    }

    return new GeoPoint(radianValue(latitude), radianValue(longitude));
  }

  private double radianValue(double coord) {
    return coord * PI / 180.0;
  }

}
