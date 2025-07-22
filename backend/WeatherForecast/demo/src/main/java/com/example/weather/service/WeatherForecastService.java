package com.example.weather.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.weather.error.WeatherForecastException;
import com.example.weather.response.FallbackJsonReader;
import com.example.weather.response.ForecastData;
import com.example.weather.response.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherForecastService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired 
    private FallbackJsonReader fallbackReader;

    @Autowired 
    private OfflineToggleService toggleService;


    public WeatherResponse getForecast(String city) {

        JsonNode root;

        if (toggleService.isOffline()) {
            root = fallbackReader.getFallbackData(city);

        } else {

            try {
                String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&cnt=24";
                //System.out.println("URL: " + url);
                ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
                root = response.getBody();
            } catch (HttpClientErrorException e) {
            
                try {
                    String errorString = e.getResponseBodyAsString();
                    //System.out.println("Raw error body: " + errorString);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode errorNode = mapper.readTree(errorString);

                    String cod = errorNode.has("cod") ? errorNode.get("cod").asText() : "500";
                    String message = errorNode.has("message") ? errorNode.get("message").asText() : "Unknown error";
                    throw new WeatherForecastException(cod, message);
                } catch (WeatherForecastException wfe) {                
                    throw wfe; 
                } catch (Exception jsonEx) {
                    throw new WeatherForecastException("500", "Error parsing error response: " + jsonEx.getMessage());
                }
            }
        }
        return parseResponse(city, root);
        
    }

    private WeatherResponse parseResponse(String city, JsonNode root) {
        List<ForecastData> list = new ArrayList<>();
        for (JsonNode node : root.get("list")) {
            String date = node.get("dt_txt").asText().substring(0, 10);
            double temp = node.get("main").get("temp").asDouble();
            double tempHigh = node.get("main").get("temp_max").asDouble();
            double tempLow = node.get("main").get("temp_min").asDouble();
            double wind = node.get("wind").get("speed").asDouble();
            String weather = node.get("weather").get(0).get("main").asText();

            ForecastData fd = new ForecastData(date, tempHigh, tempLow, getMessage(temp, weather, wind));
            list.add(fd);
        }

        // Reduce to 3 dates
        Map<String, ForecastData> daily = list.stream()
            .collect(Collectors.toMap(ForecastData::getDate, fd -> fd, (a, b) -> {
                a.setTempHigh(Math.max(a.getTempHigh(), b.getTempHigh()));
                a.setTempLow(Math.min(a.getTempLow(), b.getTempLow()));
                return a;
            }));

        WeatherResponse result = new WeatherResponse();
        result.setCity(city);
        //result.setForecast(new ArrayList<>(daily.values()));
        List<ForecastData> limitedForecast = daily.values().stream()
            .sorted((a, b) -> a.getDate().compareTo(b.getDate()))
            .limit(3)
            .collect(Collectors.toList());
        result.setForecast(limitedForecast);
        return result;
    }

    private String getMessage(double temp, String condition, double wind) {
        if (condition.toLowerCase().contains("rain")) return "Carry umbrella";
        if (temp > 40) return "Use sunscreen lotion";
        if (wind > 10) return "It's too windy, watch out!";
        if (condition.toLowerCase().contains("thunderstorm")) return "Don't step out! A Storm is brewing!";        
        return "Weather looks fine";
    }
}
