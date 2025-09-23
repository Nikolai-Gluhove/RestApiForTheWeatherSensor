package ru.alishev.springcourse.FirstRestApp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Check;

public class MeasurementDTO {

    private double value;

    @NotEmpty
    private boolean raining;


    @Valid
    private String sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double value, boolean raining, String sensor) {
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

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
