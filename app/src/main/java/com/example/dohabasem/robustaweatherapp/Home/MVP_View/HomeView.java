package com.example.dohabasem.robustaweatherapp.Home.MVP_View;

import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;

import java.util.ArrayList;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public interface HomeView {

    void showAllWeatherItems(ArrayList<WeatherItem> weatherItems);

    void navigateToWeatherItem(WeatherItem weatherItem);

    void navigateToNewWeatherItem();
}
