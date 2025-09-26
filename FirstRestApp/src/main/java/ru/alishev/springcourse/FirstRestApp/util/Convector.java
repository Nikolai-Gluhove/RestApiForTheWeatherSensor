package ru.alishev.springcourse.FirstRestApp.util;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;

@Configuration
public class Convector {
    private final ModelMapper modelMapper;

    @Autowired
    public Convector(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Bean
    public Sensor convectSensorAndSensorDTO(@Valid SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @Bean
    public SensorDTO convectSensorAndSensorDTO(@Valid Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
