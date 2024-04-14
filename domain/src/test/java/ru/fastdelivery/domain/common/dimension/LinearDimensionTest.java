package ru.fastdelivery.domain.common.dimension;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LinearDimensionTest {

  @ParameterizedTest(name = "Округление линейных размеров до полных 50 мм")
  @CsvSource({"0, 0", "28, 50", "270, 300", "800, 800"})
  void testForVolumeCalc(int actual, int expected) {
    var actualDimension = new LinearDimension(actual);
    var expectedDimension = new LinearDimension(expected);
    assertEquals(actualDimension.forVolumeCalc().value(), expectedDimension.value());
  }

}