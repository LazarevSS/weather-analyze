package ru.weatheranalyze.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weatheranalyze.model.WeatherStatistic;
import ru.weatheranalyze.rest.dto.WeatherPeriodResponseDto;
import ru.weatheranalyze.service.WeatherAnalyzeService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class WeatherAnalyzeController {

    private final WeatherAnalyzeService weatherAnalyzeService;

    @Autowired
    public WeatherAnalyzeController(WeatherAnalyzeService weatherAnalyzeService) {
        this.weatherAnalyzeService = weatherAnalyzeService;
    }

    @GetMapping("/weather-analyze/period")
    public ResponseEntity<WeatherPeriodResponseDto> getWeatherByPeriod(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to
    ) {
        List<WeatherStatistic> weatherStatistics =
                weatherAnalyzeService.getByPeriod(LocalDateTime.parse(from), LocalDateTime.parse(to));
        WeatherPeriodResponseDto dto = new WeatherPeriodResponseDto();
        dto.setMaxTemperature(weatherAnalyzeService.maxTemperature(weatherStatistics));
        dto.setMinTemperature(weatherAnalyzeService.minTemperature(weatherStatistics));
        dto.setAverageTemperature(weatherAnalyzeService.averageTemperature(weatherStatistics));
        dto.setPrecipitation(weatherAnalyzeService.isPrecipitation(weatherStatistics));
        return ResponseEntity.ok(dto);
    }
}
