package ru.weatheranalyze.sheduler.job;

import lombok.val;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import ru.weatheranalyze.client.WeatherClient;
import ru.weatheranalyze.client.darkskyweather.dto.DarkSkyWeatherResponseDto;
import ru.weatheranalyze.model.WeatherStatistic;
import ru.weatheranalyze.service.WeatherAnalyzeService;

import java.time.LocalDateTime;
import java.util.Objects;

@DisallowConcurrentExecution
public class CurrentWeatherJob extends QuartzJobBean {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private WeatherAnalyzeService weatherAnalyzeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        val responseDto = (DarkSkyWeatherResponseDto) weatherClient.getCurrentWeather();
        val temperature = responseDto.getInfo().getTemperature();
        val isPrecipitation = Objects.nonNull(responseDto.getInfo().getPrecipitationType());
        val statistic = new WeatherStatistic(temperature, isPrecipitation, LocalDateTime.now());
        weatherAnalyzeService.save(statistic);
    }
}
