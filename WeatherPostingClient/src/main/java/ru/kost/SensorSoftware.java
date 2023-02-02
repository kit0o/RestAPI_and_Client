package ru.kost;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.kost.dto.MeasurementDTO;
import ru.kost.dto.ReceiveMeasuresDTO;
import ru.kost.dto.SensorDTO;

import java.util.*;

public class SensorSoftware {
    //Method below is used to send measurements to the server
    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            send();
        }
    }

    //The method below is used to set the sensor's name, temperature and rainFactor to our measurementDTO object
    private static MeasurementDTO create(MeasurementDTO measurementDTO) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName("New sensor name");
        measurementDTO.setDetector(sensorDTO);
        measurementDTO.setValue(getRandom());
        measurementDTO.setRaining(rainFactor());
        return measurementDTO;
    }

    //Method below is used to set a random number from -100 to 100 (set a temperature)
    private static int getRandom() {
        return (int) ((Math.random() * (100)) + (Math.random() * (-100)));
    }

    //The method below is used to set a random rain factor
    private static String rainFactor() {
        Random random = new Random();
        String isRain = "";
        boolean itRains = random.nextBoolean();
        if (itRains) {
            isRain = "true";
        } else {
            isRain = "false";
        }
        return isRain;
    }
    //The method below is to send measurements to the server
    private static void send() {

        RestTemplate restTemplate = new RestTemplate(); //set RestTemplate

        MeasurementDTO measurementDTO = new MeasurementDTO(); //new object

        HttpHeaders headers = new HttpHeaders();    //set headers
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "http://localhost:8080/measurements/add";  //set url

        Map<String, Object> jsonToSend = new HashMap<>();  //prepare a HashMap for Json

        MeasurementDTO measurement = create(measurementDTO);  //set values for our MeasurementDTO
        jsonToSend.put("value", measurement.getValue());
        jsonToSend.put("detector", measurement.getDetector().getName());
        jsonToSend.put("raining", measurement.getRaining());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToSend, headers);  //create HttpEntity to
                                                                                          // form both json and
                                                                                          // header into a json

        restTemplate.postForObject(url, request, String.class);     //send a post-request
    }
}












