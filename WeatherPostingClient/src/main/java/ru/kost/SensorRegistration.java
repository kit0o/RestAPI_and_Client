package ru.kost;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.kost.dto.SensorDTO;

import java.util.HashMap;
import java.util.Map;

public class SensorRegistration {
    //The method below is used to register our new sensor
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        SensorDTO sensorDTO = new SensorDTO();

        String url = "http://localhost:8080/sensors/registration";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> jsonToSend = new HashMap<>();
        jsonToSend.put("name", createSensor(sensorDTO).getName());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToSend, headers);

        String response = restTemplate.postForObject(url, request, String.class);
    }
    //Method below is used to set a name for our sensor
    private static SensorDTO createSensor(SensorDTO sensorDTO) {
        sensorDTO.setName("Smarty sensor");
        return sensorDTO;
    }
}
