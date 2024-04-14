package ru.fastdelivery.domain.common.dimension;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Общий класс, описывающий габариты упаковки
 *
 * @param length    - длина, мм
 * @param width     - ширина, мм
 * @param height    - высота, мм
 */

public record PackVolume(LinearDimension length, LinearDimension width, LinearDimension height) {

    public Double cubicmeters() {
        double douVolume = length.forVolumeCalc().value() * width.forVolumeCalc().value() * height.forVolumeCalc().value() / 1000_000_000d;
        return new BigDecimal(Double.toString(douVolume)).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }
}
