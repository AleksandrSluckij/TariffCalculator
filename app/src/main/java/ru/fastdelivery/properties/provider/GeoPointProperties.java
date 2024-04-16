package ru.fastdelivery.properties.provider;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.route.GeoPointPropertiesProvider;

/**
 * Настройки диапазона координат из конфига
 */
@Configuration
@ConfigurationProperties("boundary-coordinates")
@Setter

public class GeoPointProperties implements GeoPointPropertiesProvider {
  private double latitudeMinValue;
  private double latitudeMaxValue;
  private double longitudeMinValue;
  private double longitudeMaxValue;

  @Override
  public boolean outOfLatitudeLimits(double latitude) {
    return latitude < latitudeMinValue || latitude > latitudeMaxValue;
  }

  @Override
  public boolean outOfLongitudeLimits(double longitude) {
    return longitude < longitudeMinValue || longitude > longitudeMaxValue;
  }
}
