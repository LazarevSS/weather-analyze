package ru.weatheranalyze.client.darkskyweather;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import ru.weatheranalyze.client.WeatherClient;
import ru.weatheranalyze.client.darkskyweather.dto.DarkSkyWeatherResponseDto;
import ru.weatheranalyze.config.DarkSkyWeatherSetting;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class DarkSkyWeatherClientImpl implements WeatherClient {

    private final RestOperations rest;

    private final DarkSkyWeatherSetting setting;

    @Override
    public DarkSkyWeatherResponseDto getCurrentWeather() {
        val urlParams = new HashMap<String, String>();
        urlParams.put("apiKey", setting.getApiKey());
        urlParams.put("latitude", setting.getLocationLatitude());
        urlParams.put("longitude", setting.getLocationLongitude());
        val uri = UriComponentsBuilder.fromHttpUrl("https://api.darksky.net/forecast/{apiKey}/{latitude}, {longitude}")
                .queryParam("exclude", "minutely,hourly,daily,alerts,flags")
                .buildAndExpand(urlParams)
                .toUri();
        val request = RequestEntity.get(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return rest.exchange(request, DarkSkyWeatherResponseDto.class).getBody();
    }
}
