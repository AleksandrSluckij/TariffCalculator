package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;

    public Price calc(Shipment shipment) {
        var weightCalculatedPrice = weightPriceProvider.costPerKg().multiply(shipment.weightAllPackages().kilograms());
        var volumeCalculatedPrice = weightPriceProvider.costPerKubM().multiply(shipment.volumeAllPackages());
        var minimalPrice = weightPriceProvider.minimalPrice();

        return weightCalculatedPrice
                .max(volumeCalculatedPrice)
                .max(minimalPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
