package ru.alishev.springcourse.FirstRestApp.error;

import org.springframework.validation.BindingResult;

public class MeasurementValidException extends CustomError{
    public MeasurementValidException(BindingResult bindingResult) {
        super(bindingResult);
    }

    public MeasurementValidException(String message){
        super(message);
    }
}
