package ru.weatheranalyze.sheduler.common;

import org.quartz.Job;
import ru.weatheranalyze.sheduler.job.CurrentWeatherJob;


import java.util.Arrays;
import java.util.Optional;

public enum JobDefinition {

    CURRENT_WEATHER("currentWeather", CurrentWeatherJob.class);

    private String name;
    private Class<? extends Job> clazz;

    JobDefinition(String name, Class<? extends Job> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Job> getClazz() {
        return clazz;
    }

    public static Optional<JobDefinition> fromValue(String name) {
        return Arrays.stream(JobDefinition.values())
            .filter(definition -> definition.name.equalsIgnoreCase(name))
            .findFirst();
    }
}
