package ru.weatheranalyze.client.darkskyweather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Response from Dark sky weather service")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DarkSkyWeatherResponseDto {

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @ApiModelProperty(value = "Weather's information")
    @JsonProperty("currently")
    private DarkSkyWeatherInfoDto info;
}
