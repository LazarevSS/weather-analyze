package ru.weatheranalyze.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.weatheranalyze.model.WeatherStatistic;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WeatherStatisticRepository class, ")
@DataJpaTest
class WeatherStatisticRepositoryTest {

    @Autowired
    private WeatherStatisticRepository weatherStatisticRepository;

    @DisplayName("the findByCreatedDateBetween method")
    @Test
    void findByCreatedDateBetween() {
        weatherStatisticRepository.save(weatherStatistic());
        val entityList = weatherStatisticRepository
                .findByCreatedDateBetween(LocalDateTime.of(2019, 9, 1, 0, 0), LocalDateTime.now());
        assertThat(entityList.isEmpty()).isFalse();
    }

    private WeatherStatistic weatherStatistic() {
        val entity = new WeatherStatistic();
        entity.setTemperature(40);
        entity.setPrecipitation(true);
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
}