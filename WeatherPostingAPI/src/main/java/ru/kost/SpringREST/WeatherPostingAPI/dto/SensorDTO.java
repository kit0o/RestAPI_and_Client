package ru.kost.SpringREST.WeatherPostingAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Its name shouldn't be empty")
    @Size(min = 3, max = 30, message = "Sensor's name should be between 2 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
