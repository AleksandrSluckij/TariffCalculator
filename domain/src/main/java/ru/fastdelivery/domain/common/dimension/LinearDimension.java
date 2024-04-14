package ru.fastdelivery.domain.common.dimension;

/**
 * Общий класс длины
 *
 * @param value измеренная длина в мм
 */

public record LinearDimension(Integer value) {

    private static final int ROUND_MODULE = 50;     // Maybe later move to properties ???

    public LinearDimension {
        if (!isLengthInBounds(value)) {
            throw new IllegalArgumentException("Each dimension cannot be less than 0 mm and more than 1500 mm");
        }
    }

    private static boolean isLengthInBounds (Integer length) {
        return length >= 0 && length <= 1500;
    }

    public LinearDimension forVolumeCalc () {
        return new LinearDimension(roundUp(this.value));
    }

    private Integer roundUp(Integer value) {
        return value % ROUND_MODULE == 0 ? value : (value / ROUND_MODULE + 1) * ROUND_MODULE;
    }

}
