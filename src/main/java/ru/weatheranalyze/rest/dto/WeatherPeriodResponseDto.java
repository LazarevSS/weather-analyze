package ru.weatheranalyze.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Response for weather analyze for the period request")
@Getter
@Setter
public class WeatherPeriodResponseDto {

    @ApiModelProperty(value = "Maximum temperature in degrees Fahrenheit", example = "40")
    @JsonProperty("maxTemperature")
    private double maxTemperature;

    @ApiModelProperty(value = "Minimum temperature in degrees Fahrenheit", example = "40")
    @JsonProperty("minTemperature")
    private double minTemperature;

    @ApiModelProperty(value = "Average temperature in degrees Fahrenheit", example = "40")
    @JsonProperty("averageTemperature")
    private double averageTemperature;

    @ApiModelProperty(value = "Were there any precipitation", example = "true")
    @JsonProperty("isPrecipitation")
    private boolean isPrecipitation;
}
