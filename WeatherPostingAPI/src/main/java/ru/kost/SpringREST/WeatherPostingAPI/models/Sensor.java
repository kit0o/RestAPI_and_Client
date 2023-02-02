package ru.kost.SpringREST.WeatherPostingAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="Sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Its name shouldn't be empty")
    @Size(min = 3, max = 30, message = "Sensor's name should be between 2 and 30 characters")
    private String name;
    @OneToMany(mappedBy = "detector", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Measurement> measurements;

    public Sensor(String name) {
        this.name = name;
    }
    public Sensor(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
