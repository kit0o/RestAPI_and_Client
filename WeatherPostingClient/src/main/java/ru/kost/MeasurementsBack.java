package ru.kost;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.kost.dto.MeasurementDTO;
import ru.kost.dto.ReceiveMeasuresDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MeasurementsBack {
    public static void main(String[] args) {
        receiveMeasurements();
    }
    private static List<Integer> receiveMeasurements() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/measurements";

        ReceiveMeasuresDTO response = restTemplate.getForObject(url, ReceiveMeasuresDTO.class);

        if(response == null) {
            return Collections.emptyList();
        }
        return response.getMeasurements().stream().map(MeasurementDTO::getValue).collect(Collectors.toList());
    }
}
