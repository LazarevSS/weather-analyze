package ru.weatheranalyze.rest.dto;

public class WeatherPeriodResponseDto {

    private double maxTemperature;

    private double minTemperature;

    private double averageTemperature;

    private boolean isPrecipitation;

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public boolean isPrecipitation() {
        return isPrecipitation;
    }

    public void setPrecipitation(boolean precipitation) {
        isPrecipitation = precipitation;
    }
}
