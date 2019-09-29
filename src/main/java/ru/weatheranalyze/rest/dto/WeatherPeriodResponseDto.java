package ru.weatheranalyze.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherPeriodResponseDto {

    private double maxTemperature;

    private double minTemperature;

    private double averageTemperature;

    private boolean isPrecipitation;
}
