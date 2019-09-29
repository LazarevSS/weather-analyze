package ru.weatheranalyze.client.darkskyweather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DarkSkyWeatherInfoDto {

    @ApiModelProperty(value = "The type of precipitation occurring at the given time", example = "rain, snow, sleet")
    @JsonProperty("precipType")
    private String precipitationType;

    @ApiModelProperty(value = "The air temperature in degrees Fahrenheit")
    @JsonProperty("temperature")
    private float temperature;
}
