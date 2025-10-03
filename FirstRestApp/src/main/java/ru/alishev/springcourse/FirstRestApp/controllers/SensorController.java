package ru.alishev.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.error.ErrorResponse;
import ru.alishev.springcourse.FirstRestApp.error.SensorValidetException;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.Convector;
import ru.alishev.springcourse.FirstRestApp.util.SensorValidator;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final Convector convector;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, Convector convector, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.convector = convector;
        this.sensorValidator = sensorValidator;
    }

    //Регистрация сенсора
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()){
            throw new SensorValidetException(bindingResult);
        }

        sensorService.save(convector.convectSensorAndSensorDTO(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
