package ru.weatheranalyze.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "weather_statistic")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class WeatherStatistic {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    @Column(name = "temperature")
    private float temperature;

    @Column(name = "precipitation")
    private boolean precipitation;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public WeatherStatistic(float temperature, boolean precipitation, LocalDateTime createdDate) {
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.createdDate = createdDate;
    }
}
