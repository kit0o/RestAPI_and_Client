package ru.kost.dto;


public class MeasurementDTO {

    private int value;

    private String raining;

    private SensorDTO detector;

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

    public SensorDTO getDetector() {
        return detector;
    }

    public void setDetector(SensorDTO detector) {
        this.detector = detector;
    }
}
