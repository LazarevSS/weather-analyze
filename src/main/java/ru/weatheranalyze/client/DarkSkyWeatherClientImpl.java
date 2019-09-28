package ru.weatheranalyze.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.weatheranalyze.client.dto.DarkSkyWeatherResponseDto;
import ru.weatheranalyze.config.DarkSkyWeatherSetting;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class DarkSkyWeatherClientImpl implements DarkSkyWeatherClient {

    private final RestOperations rest;

    private final DarkSkyWeatherSetting setting;

    @Autowired
    public DarkSkyWeatherClientImpl(DarkSkyWeatherSetting setting) {
        this.rest = new RestTemplate();
        this.setting = setting;
    }

    @Override
    public DarkSkyWeatherResponseDto getCurrentWeather() {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("apiKey", setting.getApiKey());
        urlParams.put("latitude", setting.getLocationLatitude());
        urlParams.put("longitude", setting.getLocationLongitude());
        URI uri = UriComponentsBuilder.fromHttpUrl("https://api.darksky.net/forecast/{apiKey}/{latitude}, {longitude}")
                .queryParam("exclude", "minutely,hourly,daily,alerts,flags")
                .buildAndExpand(urlParams)
                .toUri();
        RequestEntity request = RequestEntity.get(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return rest.exchange(request, DarkSkyWeatherResponseDto.class).getBody();
    }
}
