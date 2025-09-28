package ru.alishev.springcourse.FirstRestApp.error;

public class MeasurementValidException extends RuntimeException{
    public MeasurementValidException(String message) {
        super(message);
    }
}
