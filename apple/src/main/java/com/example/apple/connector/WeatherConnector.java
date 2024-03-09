package com.example.apple.connector;

import com.example.apple.model.ForeCastResponse;
import org.springframework.stereotype.Component;

import static com.example.apple.constant.CommonConstant.WEATHER_API_KEY;
import static com.example.apple.constant.CommonConstant.WEATHER_API_URL;

@Component
public class WeatherConnector {

    public ForeCastResponse fetchWeatherData(String zipcode) {
        try {
            String apiUrl = WEATHER_API_URL + "?zip=" + zipcode + ",us&appid=" + WEATHER_API_KEY;
            // Make HTTP request to weather API and return response as string
            
            return ForeCastResponse.builder().currentTemperature(72.5)
                    .highTemperature(75.0)
                    .lowTemperature(70.0)
                    .fromCache(false).build();
        } catch (Exception e) {
            return null;
        }
    }

}
