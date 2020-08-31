package com.flyable;

import com.weather.WeatherTower;

public interface Flyable {

    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);
    
}