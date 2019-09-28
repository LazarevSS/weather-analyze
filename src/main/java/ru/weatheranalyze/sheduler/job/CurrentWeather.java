package ru.weatheranalyze.sheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import ru.weatheranalyze.client.DarkSkyWeatherClient;
import ru.weatheranalyze.client.dto.DarkSkyWeatherResponseDto;
import ru.weatheranalyze.model.WeatherStatistic;
import ru.weatheranalyze.service.WeatherAnalyzeService;

import java.time.LocalDateTime;

@DisallowConcurrentExecution
public class CurrentWeather extends QuartzJobBean {

    @Autowired
    private DarkSkyWeatherClient darkSkyWeatherClient;

    @Autowired
    private WeatherAnalyzeService weatherAnalyzeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        DarkSkyWeatherResponseDto responseDto = darkSkyWeatherClient.getCurrentWeather();
        float temperature = responseDto.getInfo().getTemperature();
        boolean isPrecipitation = responseDto.getInfo().getPrecipType() != null;
        WeatherStatistic statistic = new WeatherStatistic(temperature, isPrecipitation, LocalDateTime.now());
        weatherAnalyzeService.save(statistic);
    }
}
