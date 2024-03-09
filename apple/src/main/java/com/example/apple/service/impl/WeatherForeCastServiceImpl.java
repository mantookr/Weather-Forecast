package com.example.apple.service.impl;

import com.example.apple.config.JedisConfig;
import com.example.apple.connector.WeatherConnector;
import com.example.apple.model.ForeCastResponse;
import com.example.apple.service.WeatherForecastService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import static com.example.apple.constant.CommonConstant.*;

@Service
@Log4j2
public class WeatherForeCastServiceImpl implements WeatherForecastService {

    @Autowired
    private WeatherConnector weatherConnector;

    @Autowired
    private JedisConfig jedisConfig;

    @Override
    public ForeCastResponse getWeatherForecast(String zipcode) {
        try (Jedis jedis = jedisConfig.getJedisInstance().getResource()) {
            String cachedResult = jedis.get(zipcode);

            if (!cachedResult.isEmpty()) {
                String[] cache = cachedResult.split("-");
                return ForeCastResponse.builder()
                        .currentTemperature(Double.valueOf(cache[0].split(":")[1]))
                        .highTemperature(Double.valueOf(cache[1].split(":")[1]))
                        .lowTemperature(Double.valueOf(cache[2].split(":")[1]))
                        .fromCache(true).build();
            } else {
                ForeCastResponse weatherData = weatherConnector.fetchWeatherData(zipcode);
                String cachedData = CURRENT + ":" + weatherData.getCurrentTemperature() + DASH +
                        HIGH + ":"+ weatherData.getHighTemperature() + DASH + LOW +":" + weatherData.getLowTemperature();
                if (null != weatherData) {
                    jedis.setex(zipcode, CACHE_EXPIRATION_TIME, cachedData);
                    return weatherData;
                } else {
                    return ForeCastResponse.builder().errors(ERROR_MESSAGE).build();
                }
            }
        } catch (Exception e) {
            return ForeCastResponse.builder().errors(e.getMessage()).build();
        }
    }

}
