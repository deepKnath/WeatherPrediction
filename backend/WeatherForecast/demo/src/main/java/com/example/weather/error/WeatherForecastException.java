package com.example.weather.error;

public class WeatherForecastException extends RuntimeException {
    private final String cod;

    public WeatherForecastException(String cod, String message) {
        
        super(message);
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    
}
