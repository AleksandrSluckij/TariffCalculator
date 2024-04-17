package ru.fastdelivery.domain.common.route;

import lombok.extern.slf4j.Slf4j;

/**
 * Маршрут отправления
 * @param departure точка отправки
 * @param destination точка назначения
 */
@Slf4j
public record Route(GeoPoint departure, GeoPoint destination) {

  private static final int RADIUS = 6372795;
  public int distance () {
    var dLongitude = destination.getLongitude() - departure.getLongitude();
    var dLongitudeSine = Math.sin(dLongitude);
    var dLongitudeCosine = Math.cos(dLongitude);
    var numerator = Math.sqrt(
        Math.pow(destination.getLatitudeCosine() * dLongitudeSine, 2) +
            Math.pow(departure.getLatitudeCosine() * destination.getLatitudeSine() -
                departure.getLatitudeSine() * destination.getLatitudeCosine() * dLongitudeCosine, 2)
    );
    var denominator = departure.getLatitudeSine() * destination.getLatitudeSine() +
        departure.getLatitudeCosine() * destination.getLatitudeCosine() * dLongitudeCosine;
    var angleDist = Math.atan2(numerator, denominator);
    int result = (int) Math.round(angleDist * RADIUS / 1000);
    log.info("Route length calculated: {} kilometers", result);
    return result;
  }
}
