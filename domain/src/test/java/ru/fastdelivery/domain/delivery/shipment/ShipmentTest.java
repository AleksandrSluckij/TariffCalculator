package ru.fastdelivery.domain.delivery.shipment;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimension.LinearDimension;
import ru.fastdelivery.domain.common.dimension.PackVolume;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    private static Shipment shipment;

    @BeforeAll
    static void setUp() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);
        var volume1 = new PackVolume(new LinearDimension(18), new LinearDimension(270), new LinearDimension(800));
        var volume2 = new PackVolume(new LinearDimension(128), new LinearDimension(826), new LinearDimension(473));

        var packages = List.of(new Pack(weight1, volume1), new Pack(weight2, volume2));
        shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"));
    }

    @Test
    @DisplayName("Подсчет суммарного веса")
    void whenSummarizingWeightOfAllPackages_thenReturnSum() {
        var massOfShipment = shipment.weightAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
    }

    @Test
    @DisplayName("Подсчет суммарного объема")
    void testVolumeAllPackages() {
        var volumeOfShipment = shipment.volumeAllPackages();

        assertThat(volumeOfShipment).isEqualByComparingTo(new BigDecimal("0.0758"));
    }

    @AfterAll
    static void tearDown () {
        shipment = null;
    }
}