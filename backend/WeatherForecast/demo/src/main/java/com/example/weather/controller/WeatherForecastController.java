package com.example.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather.response.WeatherResponse;
import com.example.weather.service.OfflineToggleService;
import com.example.weather.service.WeatherForecastService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/weatherForecast")
@Tag(name = "Weather Forecast API", description = "Weather Forecast API")
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherService;
    @Autowired 
    private OfflineToggleService toggleService;


    @Operation(summary = "Get 3-day weather forecast for a city")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Invalid city input"),
        @ApiResponse(responseCode = "404", description = "City not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping
    public ResponseEntity<WeatherResponse> getForecast(@RequestParam String city) {
        WeatherResponse response = weatherService.getForecast(city);
        response.add(linkTo(methodOn(WeatherForecastController.class).getForecast(city)).withSelfRel());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Toggle offline mode")
    @PostMapping("/toggle")
    public ResponseEntity<String> setOfflineMode(@RequestParam boolean offline) {
        toggleService.setOffline(offline);
        return ResponseEntity.ok("Mode set to: " + (offline ? "OFFLINE" : "ONLINE"));
    }

    @Operation(summary = "Check current mode")
    @GetMapping("/mode")
    public ResponseEntity<String> checkOfflineMode() {
        return ResponseEntity.ok("Current mode: " + (toggleService.isOffline() ? "OFFLINE" : "ONLINE"));
    }
}
