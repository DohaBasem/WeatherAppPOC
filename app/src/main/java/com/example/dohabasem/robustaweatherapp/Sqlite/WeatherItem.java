package com.example.dohabasem.robustaweatherapp.Sqlite;

import java.io.Serializable;

/**
 * Created by Doha Basem on 9/15/2017.
 */
//The model that includes the weather data
public class WeatherItem implements Serializable {

    private int itemId;

    private String imagePath;

    private String place;

    private String temperature;

    private String humidity;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
