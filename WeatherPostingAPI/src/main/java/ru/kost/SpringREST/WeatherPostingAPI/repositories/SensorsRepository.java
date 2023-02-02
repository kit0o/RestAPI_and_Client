package ru.kost.SpringREST.WeatherPostingAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kost.SpringREST.WeatherPostingAPI.models.Sensor;
@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    Sensor findByName(String name);
}
