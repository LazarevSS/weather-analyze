package ru.weatheranalyze.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DarkSkyWeatherInfoDto {

    @JsonProperty("precipType")
    private String precipType;

    @JsonProperty("temperature")
    private float temperature;

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
