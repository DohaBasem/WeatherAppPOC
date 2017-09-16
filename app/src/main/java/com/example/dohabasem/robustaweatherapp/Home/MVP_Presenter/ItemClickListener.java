package com.example.dohabasem.robustaweatherapp.Home.MVP_Presenter;

import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public interface ItemClickListener {

    void onItemClicked(WeatherItem weatherItem);

    void onAddNewWeatherItemClicked();
}
