package ru.weatheranalyze.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.dark-sky-weather")
@Setter
@Getter
public class DarkSkyWeatherSetting {

    private String locationLatitude;
    private String locationLongitude;
    private String apiKey;
}
