package ru.kost.SpringREST.WeatherPostingAPI.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import ru.kost.SpringREST.WeatherPostingAPI.models.Sensor;

public class MeasurementDTO {

    @Min(value = -100, message = "The temperature should not be lower than -100")
    @Max(value = 100, message = "The temperature should not be higher than 100")
    private int value;

    @NotEmpty(message = "It should detect the rain")
    private String raining;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Sensor detector;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public Sensor getDetector() {
        return detector;
    }

    public void setDetector(Sensor detector) {
        this.detector = detector;
    }
}
