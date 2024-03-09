package com.example.apple.service;

import com.example.apple.model.ForeCastResponse;

public interface WeatherForecastService {

    ForeCastResponse getWeatherForecast(String zipcode);
}
