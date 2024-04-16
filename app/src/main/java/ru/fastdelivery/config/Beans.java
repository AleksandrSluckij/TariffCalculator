package ru.fastdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;
import ru.fastdelivery.domain.common.route.GeoPointFactory;
import ru.fastdelivery.domain.common.route.GeoPointPropertiesProvider;
import ru.fastdelivery.usecase.TariffCalculateUseCase;
import ru.fastdelivery.usecase.PriceProvider;

/**
 * Определение реализаций бинов для всех модулей приложения
 */
@Configuration
public class Beans {

    @Bean
    public CurrencyFactory currencyFactory(CurrencyPropertiesProvider currencyProperties) {
        return new CurrencyFactory(currencyProperties);
    }

    @Bean
    public TariffCalculateUseCase tariffCalculateUseCase(PriceProvider priceProvider) {
        return new TariffCalculateUseCase(priceProvider);
    }

    @Bean
    public GeoPointFactory geoPointFactory (GeoPointPropertiesProvider geoPointPropertiesProvider) {
        return new GeoPointFactory(geoPointPropertiesProvider);
    }
}
