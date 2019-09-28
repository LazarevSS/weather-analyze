package ru.weatheranalyze.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weatheranalyze.model.WeatherStatistic;
import ru.weatheranalyze.repository.WeatherStatisticRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class WeatherAnalyzeServiceImpl implements WeatherAnalyzeService {

    private final WeatherStatisticRepository repository;

    @Override
    public List<WeatherStatistic> findAll() {
        return repository.findAll();
    }

    @Override
    public WeatherStatistic getById(UUID id) {
        return repository.findById(id)
                .orElseThrow((EntityNotFoundException::new));
    }

    @Override
    public WeatherStatistic save(WeatherStatistic weatherStatistic) {
        return repository.save(weatherStatistic);
    }

    @Override
    public void delete(UUID id) {
        WeatherStatistic entity = getById(id);
        repository.delete(entity);
    }

    @Autowired
    public WeatherAnalyzeServiceImpl(WeatherStatisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WeatherStatistic> getByPeriod(LocalDateTime from, LocalDateTime to) {
        return repository.findByCreatedDateBetween(from, to);
    }

    @Override
    public double maxTemperature(List<WeatherStatistic> weatherStatistics) {
        return weatherStatistics.stream()
                .mapToDouble(WeatherStatistic::getTemperature)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public double minTemperature(List<WeatherStatistic> weatherStatistics) {
        return weatherStatistics.stream()
                .mapToDouble(WeatherStatistic::getTemperature)
                .min()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public double averageTemperature(List<WeatherStatistic> weatherStatistics) {
        return weatherStatistics.stream()
                .mapToDouble(WeatherStatistic::getTemperature)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean isPrecipitation(List<WeatherStatistic> weatherStatistics) {
        return weatherStatistics.stream()
                .map(WeatherStatistic::isPrecipitation)
                .anyMatch(isPrecipitation -> true);
    }
}
