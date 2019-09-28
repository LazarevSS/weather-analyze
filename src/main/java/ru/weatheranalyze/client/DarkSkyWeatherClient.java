package ru.weatheranalyze.client;

import ru.weatheranalyze.client.dto.DarkSkyWeatherResponseDto;

public interface DarkSkyWeatherClient {

    DarkSkyWeatherResponseDto getCurrentWeather();
}
