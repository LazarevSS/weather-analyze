package ru.weatheranalyze.client.darkskyweather;

import lombok.val;
import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.weatheranalyze.client.WeatherClient;
import ru.weatheranalyze.client.darkskyweather.dto.DarkSkyWeatherResponseDto;
import ru.weatheranalyze.config.DarkSkyWeatherSetting;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DarkSkyWeatherClient class")
@ExtendWith(MockitoExtension.class)
class DarkSkyWeatherClientImplTest {

    private RestOperations rest = new RestTemplate();

    private DarkSkyWeatherSetting setting = initSetting();

    @InjectMocks
    private WeatherClient weatherClient = new DarkSkyWeatherClientImpl(rest, setting);

    @Test
    void getCurrentWeather() {
        var response = weatherClient.getCurrentWeather();
        assertThat(response)
                .isNotNull()
                .isInstanceOf(DarkSkyWeatherResponseDto.class);
    }

    private DarkSkyWeatherSetting initSetting() {
        val setting = new DarkSkyWeatherSetting();
        setting.setLocationLatitude("55.717453");
        setting.setLocationLongitude("37.657541");
        setting.setApiKey("a74a81239b7cf81251d83adfd5e236df");
        return setting;
    }
}