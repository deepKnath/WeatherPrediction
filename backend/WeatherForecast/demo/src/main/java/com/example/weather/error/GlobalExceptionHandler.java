package com.example.weather.error;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WeatherForecastException.class)
    public ResponseEntity<Map<String, Object>> handleWeatherForecastError(WeatherForecastException ex) {
        Map<String, Object> error = new HashMap<>();
        int statusCode;

        try {
            statusCode = Integer.parseInt(ex.getCod());

        } catch (NumberFormatException e) {
            statusCode = 500; 
        }

        HttpStatus status = HttpStatus.resolve(statusCode);
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
       
        error.put("status", status.value());
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, status);
    }
}