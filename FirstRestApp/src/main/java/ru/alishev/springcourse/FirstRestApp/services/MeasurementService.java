package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.error.MeasurementValidException;
import ru.alishev.springcourse.FirstRestApp.models.Measurement;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement){
        Optional<Sensor> sensor = sensorService.findByName(measurement.getSensor().getName());
        if (!sensor.isPresent()){
            throw new MeasurementValidException("Сенсора с именем "+measurement.getSensor().getName()+" нет в системе");
        }
        measurement.setSensor(sensor.get());
        measurement.setDateTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    public long countByRainingTrue(){
        return measurementRepository.countByRainingTrue();
    }
}
