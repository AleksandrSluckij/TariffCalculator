package ru.fastdelivery.domain.common.route;

/**
 * Создание географической точки с проверками
 */

public interface GeoPointPropertiesProvider {

  /**
   *
   * @param latitude широта точки в градусах
   * @return  находится ли широта точки в допустимых пределах
   */
  boolean outOfLatitudeLimits(double latitude);

  /**
   *
   * @param longitude долгота точки в градусах
   * @return  находится ли долгота точки в допустимых пределах
   */
  boolean outOfLongitudeLimits(double longitude);
}
