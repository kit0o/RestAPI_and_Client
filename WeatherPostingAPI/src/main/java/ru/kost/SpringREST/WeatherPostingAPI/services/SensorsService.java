package ru.kost.SpringREST.WeatherPostingAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kost.SpringREST.WeatherPostingAPI.models.Sensor;
import ru.kost.SpringREST.WeatherPostingAPI.repositories.SensorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepository sensorsRepository;
    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }
    public Sensor findOne(int id) {
        Optional<Sensor> foundOne = sensorsRepository.findById(id);
        return foundOne.orElse(null);
    }
    public Sensor findOne(String name) {
       Sensor foundByName = sensorsRepository.findByName(name);
       return foundByName;
    }
    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

}
