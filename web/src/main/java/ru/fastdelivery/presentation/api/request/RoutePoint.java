package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoutePoint(
    @Schema(description = "Широта начала/конца маршрута в пределах 45 - 65 градусов", example = "56.6745")
    double latitude,
    @Schema(description = "Долгота начала/конца маршрута в пределах 30 - 96 градусов", example = "56.6745")
    double longitude
    ) {
}
