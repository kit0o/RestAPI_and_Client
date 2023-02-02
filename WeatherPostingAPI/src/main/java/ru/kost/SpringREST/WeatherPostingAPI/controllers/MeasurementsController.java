package ru.kost.SpringREST.WeatherPostingAPI.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kost.SpringREST.WeatherPostingAPI.dto.MeasurementDTO;
import ru.kost.SpringREST.WeatherPostingAPI.dto.SensorDTO;
import ru.kost.SpringREST.WeatherPostingAPI.models.Measurement;
import ru.kost.SpringREST.WeatherPostingAPI.models.Sensor;
import ru.kost.SpringREST.WeatherPostingAPI.services.MeasurementsService;
import ru.kost.SpringREST.WeatherPostingAPI.services.SensorsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    public MeasurementsController(MeasurementsService measurementsService, SensorsService sensorsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<MeasurementDTO> findAll() {
    return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public int getRainyDays() {
        List<MeasurementDTO> allDaysList = measurementsService.findAll().stream()
                .map(this::convertToMeasurementDTO).filter(e -> e.getRaining().equals("true")).collect(Collectors.toList());
        return allDaysList.size();

    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody MeasurementDTO measurementDTO) {
        String name = convertToMeasurement(measurementDTO).getDetector().getName();
        Sensor sensor = sensorsService.findOne(name);                       //These 3 lines are for the
        int id = sensor.getId();                                            // checking whether sensor already
        measurementsService.save(convertToMeasurement(measurementDTO), id);  //exists and appointing it to the measurement
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
