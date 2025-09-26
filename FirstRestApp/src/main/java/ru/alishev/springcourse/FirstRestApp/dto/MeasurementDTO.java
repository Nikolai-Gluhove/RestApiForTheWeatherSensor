package ru.alishev.springcourse.FirstRestApp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Check;

public class MeasurementDTO {

    private double value;

    @NotEmpty
    private boolean raining;


    @Valid
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
