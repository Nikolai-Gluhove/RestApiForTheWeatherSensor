package ru.alishev.springcourse.FirstRestApp.error;

public class SensorValidetException extends RuntimeException {
    public SensorValidetException(String message) {
        super(message);
    }
}
