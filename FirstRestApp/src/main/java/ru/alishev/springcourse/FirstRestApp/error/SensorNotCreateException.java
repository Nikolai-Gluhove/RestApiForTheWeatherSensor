package ru.alishev.springcourse.FirstRestApp.error;

public class SensorNotCreateException extends RuntimeException {
    public SensorNotCreateException(String message) {
        super(message);
    }
}
