package ru.alishev.springcourse.FirstRestApp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class MeasurementDTO {

    @Range(min = -100, max = 100, message = "Значение должно быть от -100 до 100")
    @NotNull(message = "значение не может быть пустым")
    private Double value;

    @NotNull(message = "значение не может быть пустым")
    private Boolean raining;

    @Valid
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
