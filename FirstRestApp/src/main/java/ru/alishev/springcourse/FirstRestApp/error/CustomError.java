package ru.alishev.springcourse.FirstRestApp.error;

import org.springframework.validation.BindingResult;

public class CustomError extends RuntimeException{
    private BindingResult bindingResult;

    public CustomError (BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }

    public CustomError(String message) {
        super(message);
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
