package ru.fastdelivery.domain.common.route;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Общий класс географичесой точки
 */

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GeoPoint {
  double latitude;
  double longitude;

  double getLatitudeSine() {
    return Math.sin(latitude);
  }
  double getLatitudeCosine() {
    return Math.cos(latitude);
  }
}
