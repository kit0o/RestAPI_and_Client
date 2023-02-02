package ru.kost.SpringREST.WeatherPostingAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kost.SpringREST.WeatherPostingAPI.models.Measurement;
import ru.kost.SpringREST.WeatherPostingAPI.models.Sensor;
import ru.kost.SpringREST.WeatherPostingAPI.repositories.MeasurementsRepository;
import ru.kost.SpringREST.WeatherPostingAPI.repositories.SensorsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsRepository sensorsRepository;
    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsRepository sensorsRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsRepository = sensorsRepository;
    }
    @Transactional
    public void save(Measurement measurement, int id) {
        enrichMeasurement(measurement);
        measurement.setDetector(sensorsRepository.findById(id).orElse(null));
        measurementsRepository.save(measurement);
    }
    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }
    public void enrichMeasurement(Measurement measurement) {
        measurement.setPostAt(LocalDateTime.now());
    }
}
