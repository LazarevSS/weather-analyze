package ru.weatheranalyze.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DarkSkyWeatherResponseDto {

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("currently")
    private DarkSkyWeatherInfoDto info;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public DarkSkyWeatherInfoDto getInfo() {
        return info;
    }

    public void setInfo(DarkSkyWeatherInfoDto info) {
        this.info = info;
    }
}
