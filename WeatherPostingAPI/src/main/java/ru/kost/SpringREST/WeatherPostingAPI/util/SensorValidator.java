package ru.kost.SpringREST.WeatherPostingAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kost.SpringREST.WeatherPostingAPI.models.Sensor;
import ru.kost.SpringREST.WeatherPostingAPI.services.SensorsService;

@Component
public class SensorValidator implements Validator {
    private final SensorsService sensorsService;
    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorsService.findOne(sensor.getName()) != null) {
            errors.rejectValue("name", "", "This name has been already taken!");
        }
    }
}
