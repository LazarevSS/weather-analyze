package ru.weatheranalyze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.weatheranalyze.model.WeatherStatistic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface WeatherStatisticRepository extends JpaRepository<WeatherStatistic, UUID> {

    List<WeatherStatistic> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
}
