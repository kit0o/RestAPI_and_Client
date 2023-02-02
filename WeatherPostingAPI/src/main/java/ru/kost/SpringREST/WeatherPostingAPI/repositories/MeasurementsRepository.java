package ru.kost.SpringREST.WeatherPostingAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kost.SpringREST.WeatherPostingAPI.models.Measurement;
@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
