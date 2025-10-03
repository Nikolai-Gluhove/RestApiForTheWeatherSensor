package ru.alishev.springcourse.FirstRestApp.error;

import org.springframework.validation.BindingResult;

public class SensorValidetException extends CustomError {
    public SensorValidetException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
