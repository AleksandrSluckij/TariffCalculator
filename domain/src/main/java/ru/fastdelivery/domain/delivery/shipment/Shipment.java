package ru.fastdelivery.domain.delivery.shipment;

import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.dimension.PackVolume;
import ru.fastdelivery.domain.common.route.Route;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @param packages упаковки в грузе
 * @param currency валюта объявленная для груза
 * @param route маршрут перевозки
 */
@Slf4j
public record Shipment(
        List<Pack> packages,
        Currency currency,
        Route route
) {
    public Weight weightAllPackages() {
        Weight totalWeight = packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
        log.info("Shipment weight calculated {} grams", totalWeight.weightGrams());
        return totalWeight;
    }

    public BigDecimal volumeAllPackages() {
        double totalVolume = packages.stream()
                .map(Pack::volume)
                .map(PackVolume::cubicmeters)
                .reduce(0.0, Double::sum);
        log.info("Shipment volume calculated {} kub meters", totalVolume);
        return new BigDecimal(Double.toString(totalVolume)).setScale(4, RoundingMode.HALF_UP);
    }

    public int routeLength () {
        return this.route.distance();
    }
}
