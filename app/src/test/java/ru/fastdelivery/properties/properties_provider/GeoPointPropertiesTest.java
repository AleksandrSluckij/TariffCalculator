package ru.fastdelivery.properties.properties_provider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.fastdelivery.properties.provider.GeoPointProperties;

class GeoPointPropertiesTest {
  private GeoPointProperties properties;

  @BeforeEach
  void setUp() {
    properties = new GeoPointProperties();
    properties.setLatitudeMinValue(45.0);
    properties.setLatitudeMaxValue(65.0);
    properties.setLongitudeMinValue(30.0);
    properties.setLongitudeMaxValue(96.0);
  }

  @AfterEach
   void tearDown() {
    properties = null;
  }

  @ParameterizedTest(name = "Широта = {arguments} -> в допустимых пределах")
  @ValueSource(doubles = {45.0, 54.984, 64.9999})
  void testOutOfLatitudeLimitsPassed(double parameter) {
    assertFalse(properties.outOfLatitudeLimits(parameter));
  }

  @ParameterizedTest(name = "Широта = {arguments} -> вне допустимых пределов")
  @ValueSource(doubles = {43.9999, 65.001})
  void testOutOfLatitudeLimitsFiled(double parameter) {
    assertTrue(properties.outOfLatitudeLimits(parameter));
  }

  @ParameterizedTest(name = "Долгота = {arguments} -> в допустимых пределах")
  @ValueSource(doubles = {31.526, 65.213, 96.0000})
  void outOfLongitudeLimitsPassed(double parameter) {
    assertFalse(properties.outOfLongitudeLimits(parameter));
  }

  @ParameterizedTest(name = "Долгота = {arguments} -> вне допустимых пределов")
  @ValueSource(doubles = {29.997, 96.00001})
  void outOfLongitudeLimitsFiled(double parameter) {
    assertTrue(properties.outOfLongitudeLimits(parameter));
  }
}