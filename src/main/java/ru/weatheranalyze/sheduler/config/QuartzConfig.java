package ru.weatheranalyze.sheduler.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.weatheranalyze.sheduler.common.CommonJobDetailBuilder;
import ru.weatheranalyze.sheduler.common.CommonTriggerDetailBuilder;
import ru.weatheranalyze.sheduler.job.CurrentWeather;


@Configuration
public class QuartzConfig {

    @Value("${application.jobs.current-weather.triggers.cron.value}")
    private String currentWeatherCron;

    @Bean
    @ConditionalOnProperty(name = "application.jobs.current-weather.enable",
        havingValue = "true")
    public JobDetail currentWeatherJobDetail() {
        return CommonJobDetailBuilder.buildJobDetail(
                CurrentWeather.class, "currentWeather");
    }

    @Bean
    @ConditionalOnBean(name = "currentWeatherJobDetail")
    @ConditionalOnProperty(name = "application.jobs.current-weather.triggers.cron.enable",
        havingValue = "true")
    public Trigger currentWeatherTrigger() {
        return CommonTriggerDetailBuilder.buildTrigger(
                currentWeatherCron, currentWeatherJobDetail(),
            "currentWeatherTrigger");
    }
}
