package com.example.weather.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FallbackJsonReader {
    public JsonNode getFallbackData(String city) {
        ObjectMapper mapper = new ObjectMapper();
        String dummy = """
            {
              "cod": "200",
              "message": 0,
              "cnt": 24,
              "list": [
                {
                  "dt": 1753174800,
                  "main": {
                    "temp": 291.54,
                    "feels_like": 291.52,
                    "temp_min": 291.54,
                    "temp_max": 293.91,
                    "pressure": 1008,
                    "sea_level": 1008,
                    "grnd_level": 1005,
                    "humidity": 80,
                    "temp_kf": -2.37
                  },
                  "weather": [
                    {
                      "id": 802,
                      "main": "Clouds",
                      "description": "scattered clouds",
                      "icon": "03d"
                    }
                  ],
                  "clouds": {
                    "all": 50
                  },
                  "wind": {
                    "speed": 3.65,
                    "deg": 290,
                    "gust": 5.72
                  },
                  "visibility": 10000,
                  "pop": 0,
                  "sys": {
                    "pod": "d"
                  },
                  "dt_txt": "2025-07-22 09:00:00"
                },
                {
                  "dt": 1753185600,
                  "main": {
                    "temp": 294.04,
                    "feels_like": 293.99,
                    "temp_min": 294.04,
                    "temp_max": 295.88,
                    "pressure": 1009,
                    "sea_level": 1009,
                    "grnd_level": 1006,
                    "humidity": 69,
                    "temp_kf": -1.84
                  },
                  "weather": [
                    {
                      "id": 500,
                      "main": "Rain",
                      "description": "light rain",
                      "icon": "10d"
                    }
                  ],
                  "clouds": {
                    "all": 61
                  },
                  "wind": {
                    "speed": 4.3,
                    "deg": 269,
                    "gust": 6.13
                  },
                  "visibility": 10000,
                  "pop": 0.58,
                  "rain": {
                    "3h": 0.5
                  },
                  "sys": {
                    "pod": "d"
                  },
                  "dt_txt": "2025-07-22 12:00:00"
                }
              ],
              "city": {
                "id": 2643743,
                "name": "London",
                "coord": {
                  "lat": 51.5085,
                  "lon": -0.1257
                },
                "country": "GB",
                "population": 1000000,
                "timezone": 3600,
                "sunrise": 1753157389,
                "sunset": 1753214625
              }
            }
        """;
        try {
            return mapper.readTree(dummy);
        } catch (Exception e) {
            throw new RuntimeException("Error reading fallback JSON");
        }
    }
}
