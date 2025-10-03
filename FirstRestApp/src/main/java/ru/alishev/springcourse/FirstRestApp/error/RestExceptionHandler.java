package ru.alishev.springcourse.FirstRestApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    //ошибка валидации сенсора и данных
    @ExceptionHandler({SensorValidetException.class, MeasurementValidException.class})
    private ResponseEntity<ErrorResponse> handleException(CustomError e){
        ErrorResponse response = new ErrorResponse(getMessage(e), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    //внутренний метод для получения всех ошибок
    private String getMessage(CustomError customError){
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = customError.getBindingResult().getFieldErrors();
        for (FieldError error : errors){
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        return errorMsg.toString();
    }
}
