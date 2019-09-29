package ru.weatheranalyze.rest;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weatheranalyze.rest.dto.WeatherPeriodResponseDto;
import ru.weatheranalyze.service.WeatherAnalyzeService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class WeatherAnalyzeController {

    private final WeatherAnalyzeService weatherAnalyzeService;

    @GetMapping("/weather-analyze/period")
    public ResponseEntity<WeatherPeriodResponseDto> getWeatherByPeriod(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to
    ) {
        val weatherStatisticList =
                weatherAnalyzeService.getByPeriod(LocalDateTime.parse(from), LocalDateTime.parse(to));
        val responseDto = new WeatherPeriodResponseDto();
        responseDto.setMaxTemperature(weatherAnalyzeService.maxTemperature(weatherStatisticList));
        responseDto.setMinTemperature(weatherAnalyzeService.minTemperature(weatherStatisticList));
        responseDto.setAverageTemperature(weatherAnalyzeService.averageTemperature(weatherStatisticList));
        responseDto.setPrecipitation(weatherAnalyzeService.isPrecipitation(weatherStatisticList));
        return ResponseEntity.ok(responseDto);
    }
}
