package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final PriceProvider priceProvider;

    public Price calc(Shipment shipment) {
        var weightCalculatedPrice = priceProvider.costPerKg().multiply(shipment.weightAllPackages().kilograms());
        var volumeCalculatedPrice = priceProvider.costPerKubM().multiply(shipment.volumeAllPackages());
        var minimalPrice = priceProvider.minimalPrice();
        var basePrice = weightCalculatedPrice.max(volumeCalculatedPrice).max(minimalPrice);
        return  routePriceCorrection(shipment.routeLength(), basePrice);
    }

    Price routePriceCorrection(int routeLength, Price basePrice) {
        final int MIN_ROUTE_LENGTH = 450;       // Move to config?
        if (routeLength > MIN_ROUTE_LENGTH) {
            var amountCorrected = basePrice.amount()
                    .multiply(BigDecimal.valueOf((double) routeLength / MIN_ROUTE_LENGTH))
                    .setScale(2, RoundingMode.CEILING);
            basePrice = new Price(amountCorrected, basePrice.currency());
        }
        return basePrice;
    }

    public Price minimalPrice() {
        return priceProvider.minimalPrice();
    }

}
