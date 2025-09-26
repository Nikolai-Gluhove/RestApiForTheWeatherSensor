package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;

public class SensorValidator implements Validator {

    private final SensorService sensorService;
    private final Convector convector;

    @Autowired
    public SensorValidator(SensorService sensorService, Convector convector) {
        this.sensorService = sensorService;
        this.convector = convector;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = convector.convectSensorAndSensorDTO((SensorDTO) target);


        if (sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "400", "Данное имя занято.");
        }
    }
}
