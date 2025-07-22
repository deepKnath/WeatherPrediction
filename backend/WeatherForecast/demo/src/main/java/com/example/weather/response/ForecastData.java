package com.example.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastData {
    private String date;
    private double tempHigh;
    private double tempLow;
    private String message;
}
