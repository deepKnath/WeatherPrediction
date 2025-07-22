package com.example.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse extends RepresentationModel<WeatherResponse> {
    private String city;
    private List<ForecastData> forecast;
}