package ru.fastdelivery.domain.delivery.pack;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.dimension.LinearDimension;
import ru.fastdelivery.domain.common.dimension.PackVolume;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PackTest {

    @Test
    void whenWeightMoreThanMaxWeight_thenThrowException() {
        var weight = new Weight(BigInteger.valueOf(150_001));
        var volume = new PackVolume(new LinearDimension(100), new LinearDimension(200), new LinearDimension(300));
        assertThatThrownBy(() -> new Pack(weight, volume))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWeightLessThanMaxWeight_thenObjectCreated() {
        var volume = new PackVolume(new LinearDimension(100), new LinearDimension(200), new LinearDimension(300));
        var actual = new Pack(new Weight(BigInteger.valueOf(1_000)), volume);
        assertThat(actual.weight()).isEqualTo(new Weight(BigInteger.valueOf(1_000)));
    }
}