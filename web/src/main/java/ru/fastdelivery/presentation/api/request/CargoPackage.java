package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public record CargoPackage(
        @Schema(description = "Вес упаковки, граммы", example = "5667.45")
        BigInteger weight,
        @Schema(description = "Длина упаковки, мм, от 0 до 1500", example = "893")
        Integer length,
        @Schema(description = "Ширина упаковки, мм, от 0 до 1500", example = "575")
        Integer width,
        @Schema(description = "Высота упаковки, мм, от 0 до 1500", example = "423")
        Integer height
) {
}
