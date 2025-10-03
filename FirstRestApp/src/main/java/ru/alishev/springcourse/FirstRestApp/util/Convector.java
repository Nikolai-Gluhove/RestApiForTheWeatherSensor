package ru.alishev.springcourse.FirstRestApp.util;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Measurement;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;

@Configuration
public class Convector {
    private final ModelMapper modelMapper;

    @Autowired
    public Convector(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Sensor convectSensorAndSensorDTO(@Valid SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public SensorDTO convectSensorAndSensorDTO(@Valid Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Measurement convectMeasurementAndMeasurementDTO(@Valid MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convectMeasurementAndMeasurementDTO(@Valid Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
