package ru.fastdelivery.domain.delivery.pack;

import ru.fastdelivery.domain.common.dimension.PackVolume;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

/**
 * Упаковка груза
 *
 * @param weight вес товаров в упаковке
 * @param volume    - запись типа PackVolume, описывающая габариты пакета
 */
public record Pack(Weight weight,
                   PackVolume volume) {

    private static final Weight maxWeight = new Weight(BigInteger.valueOf(150_000));

    public Pack {
        if (weight.greaterThan(maxWeight)) {
            throw new IllegalArgumentException("Package can't be more than " + maxWeight);
        }
    }
}
