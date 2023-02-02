package ru.kost.SpringREST.WeatherPostingAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    @Min(value = -100, message = "The temperature should not be lower than -100")
    @Max(value = 100, message = "The temperature should not be higher than 100")
    private int value;
    @Column(name = "raining")
    @NotEmpty(message = "It should detect the rain")
    private String raining;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor detector;

    @Column(name = "post_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime postAt;
    @Transient
    private boolean rain;

    public Measurement(int value, String raining) {
        this.value = value;
        this.raining = raining;
    }
    public Measurement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getPostAt() {
        return postAt;
    }

    public void setPostAt(LocalDateTime postAt) {
        this.postAt = postAt;
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }
}
