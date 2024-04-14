package ru.fastdelivery.domain.common.dimension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LinearDimensionFactoryTest {

  @ParameterizedTest(name = "Успешное создание линейного размера из длины {arguments} мм")
  @ValueSource(ints = {0, 375, 1233})
  void whenParametersInRangeCreationSuccess (int length) {
    var dimension = new LinearDimension(length);

    assertNotNull(dimension);
    assertEquals(dimension.value(), length);
  }

  @ParameterizedTest(name = "Неуспешное создание линейного размера из длины {arguments} мм")
  @ValueSource(ints = {-5, 1600})
  void whenParametersOutOfRangeCreationFiled (int length) {
    assertThatThrownBy(() -> new LinearDimension(length))
        .isInstanceOf(IllegalArgumentException.class);
  }

}