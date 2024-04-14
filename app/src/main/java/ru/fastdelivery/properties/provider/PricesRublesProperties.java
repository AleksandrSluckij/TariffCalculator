package ru.fastdelivery.properties.provider;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.usecase.WeightPriceProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Настройки базовых цен стоимости перевозки из конфига
 */
@ConfigurationProperties("cost.rub")
@Setter
public class PricesRublesProperties implements WeightPriceProvider {

    private BigDecimal perKg;
    private BigDecimal minimal;
    private BigDecimal perKubM;

    @Autowired
    private CurrencyFactory currencyFactory;

    @Override
    public Price costPerKg() {
        return new Price(perKg.setScale(2, RoundingMode.HALF_UP), currencyFactory.create("RUB"));
    }

    public Price costPerKubM () {
        return new Price(perKubM.setScale(2, RoundingMode.HALF_UP), currencyFactory.create("RUB"));
    }

    @Override
    public Price minimalPrice() {
        return new Price(minimal.setScale(2, RoundingMode.HALF_UP), currencyFactory.create("RUB"));
    }
}
