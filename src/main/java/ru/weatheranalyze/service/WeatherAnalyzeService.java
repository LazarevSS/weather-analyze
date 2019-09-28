package ru.weatheranalyze.service;

import ru.weatheranalyze.model.WeatherStatistic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface WeatherAnalyzeService {

    List<WeatherStatistic> findAll();

    WeatherStatistic getById(UUID id);

    WeatherStatistic save(WeatherStatistic weatherStatistic);

    void delete(UUID id);

    List<WeatherStatistic> getByPeriod(LocalDateTime from, LocalDateTime to);

    double maxTemperature(List<WeatherStatistic> weatherStatistics);

    double minTemperature(List<WeatherStatistic> weatherStatistics);

    double averageTemperature(List<WeatherStatistic> weatherStatistics);

    boolean isPrecipitation(List<WeatherStatistic> weatherStatistics);
}
