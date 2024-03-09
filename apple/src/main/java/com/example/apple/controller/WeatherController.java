package com.example.apple.controller;

import com.example.apple.model.ForeCastResponse;
import com.example.apple.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WeatherController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping("/weather")
    public ForeCastResponse getWeatherForecast(@RequestParam(required = true) String zipcode) {

         return weatherForecastService.getWeatherForecast(zipcode);
    }

}
