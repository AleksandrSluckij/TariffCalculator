package ru.fastdelivery.domain.common.dimension;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PackVolumeTest {

  @ParameterizedTest(name = "Расчет объема упаковки с учетом округления габаритов")
  @CsvSource({"25, 270, 800, 0.012",
      "128, 826, 473, 0.0638"})
  void testCubicmeters(int length, int width, int height, Double volume) {
    var packVolume = new PackVolume(new LinearDimension(length),
        new LinearDimension(width), new LinearDimension(height));

    assertEquals(packVolume.cubicmeters(), volume);
  }
}