package ru.alishev.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsResponse;
import ru.alishev.springcourse.FirstRestApp.error.ErrorResponse;
import ru.alishev.springcourse.FirstRestApp.error.MeasurementValidException;
import ru.alishev.springcourse.FirstRestApp.services.MeasurementService;
import ru.alishev.springcourse.FirstRestApp.util.Convector;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final Convector convector;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, Convector convector, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.convector = convector;
        this.measurementValidator = measurementValidator;
    }

    //добавление изменения
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult){

        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new MeasurementValidException(errorMsg.toString());
        }

        //нужна кастомна конвертация
        measurementService.save(convector.convectMeasurementAndMeasurementDTO(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementValidException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //получить все изменения
    @GetMapping
    public MeasurementsResponse getAllMeasurements(){
//        return measurementService.findAll().stream().map(convector::convectMeasurementAndMeasurementDTO).collect(Collectors.toList());
        return new MeasurementsResponse(measurementService.findAll().stream().map(convector::convectMeasurementAndMeasurementDTO).collect(Collectors.toList()));
    }

    //получить количество дождливых дней
    @GetMapping("/rainyDaysCount")
    public long getNumberOfRainyDays(){
        return measurementService.countByRainingTrue();
    }
}
