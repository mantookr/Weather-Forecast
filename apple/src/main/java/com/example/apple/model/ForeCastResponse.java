package com.example.apple.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeCastResponse {
    private Double currentTemperature;
    private Double highTemperature;
    private Double lowTemperature;
    private Boolean fromCache;
    private String errors;
}
