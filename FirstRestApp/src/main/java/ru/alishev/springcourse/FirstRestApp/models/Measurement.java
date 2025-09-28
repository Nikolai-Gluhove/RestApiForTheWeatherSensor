package ru.alishev.springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @Range(min = -100, max = 100, message = "Значение должно быть от -100 до 100")
    @NotNull(message = "значение не может быть пустым")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "значение не может быть пустым")
    private Boolean raining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = true)
    private Sensor sensor;

    @Column(name = "data_time")
    private LocalDateTime dateTime;

    public Measurement() {
    }

    public Measurement(Double value, Boolean raining, Sensor sensor, LocalDateTime dateTime) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
