package ru.weatheranalyze.client;

public interface WeatherClient<T> {

    T getCurrentWeather();
}
