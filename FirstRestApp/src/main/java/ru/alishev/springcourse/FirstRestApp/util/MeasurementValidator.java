package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO)  target;

        if (!sensorService.findByName(measurementDTO.getSensor().getName()).isPresent()){
            errors.rejectValue("sensor.name", "400", "Сенсора "+measurementDTO.getSensor().getName()+" нет в системе");
        }
    }
}
