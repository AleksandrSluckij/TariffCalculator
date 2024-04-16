package ru.fastdelivery.usecase;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimension.LinearDimension;
import ru.fastdelivery.domain.common.dimension.PackVolume;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.common.route.Route;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TariffCalculateUseCaseTest {

    final PriceProvider priceProvider = mock(PriceProvider.class);
    final Currency currency = new CurrencyFactory(code -> true).create("RUB");

    final TariffCalculateUseCase tariffCalculateUseCase = new TariffCalculateUseCase(priceProvider);

    @ParameterizedTest(name = "Расчет стоимости доставки без учета маршрута -> успешно")
    @CsvSource({"5, 80, 6", "5, 100, 6.38"})
    void whenCalculatePrice_thenSuccess(int perKg, int perKubm, BigDecimal expected) {
        var minimalPrice = new Price(BigDecimal.valueOf(3), currency);
        var pricePerKg = new Price(BigDecimal.valueOf(perKg), currency);
        var pricePerKubM = new Price(BigDecimal.valueOf(perKubm), currency);
        var volume = new PackVolume(new LinearDimension(128), new LinearDimension(826), new LinearDimension(473));

        when(priceProvider.minimalPrice()).thenReturn(minimalPrice);
        when(priceProvider.costPerKg()).thenReturn(pricePerKg);
        when(priceProvider.costPerKubM()).thenReturn(pricePerKubM);

        var shipment = new Shipment(List.of(new Pack(new Weight(BigInteger.valueOf(1200)), volume)),
                currency, Mockito.mock(Route.class));
        var expectedPrice = new Price(expected, currency);

        var actualPrice = tariffCalculateUseCase.calc(shipment);

        assertThat(actualPrice).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expectedPrice);
    }

    @Test
    @DisplayName("Получение минимальной стоимости -> успешно")
    void whenMinimalPrice_thenSuccess() {
        BigDecimal minimalValue = BigDecimal.TEN;
        var minimalPrice = new Price(minimalValue, currency);
        when(priceProvider.minimalPrice()).thenReturn(minimalPrice);

        var actual = tariffCalculateUseCase.minimalPrice();

        assertThat(actual).isEqualTo(minimalPrice);
    }

    @ParameterizedTest(name = "Корректировка стоимости по длине маршрута")
    @CsvSource({"500, 345, 383.34", "200, 788.23, 788.23"})
    void testRoutePriceCorrection(int routeLength, String basePriceString, String estimatedPriceString) {
        var basePrice = new Price(new BigDecimal(basePriceString), currency);
        var correctedPrice = tariffCalculateUseCase.routePriceCorrection(routeLength, basePrice);
        var estimatedPrice = new Price(new BigDecimal(estimatedPriceString), currency);

        assertThat(correctedPrice).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(estimatedPrice);
    }
}