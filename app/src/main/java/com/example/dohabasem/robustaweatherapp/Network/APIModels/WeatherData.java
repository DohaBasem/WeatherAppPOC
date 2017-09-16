package com.example.dohabasem.robustaweatherapp.Network.APIModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class WeatherData {

    @SerializedName("dt")
    @Expose
    public long dt;
    @SerializedName("main")
    @Expose
    public MainData mainData;
    @SerializedName("weather")
    @Expose
    public java.util.List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("dt_txt")
    @Expose
    public String dtTxt;

    public MainData getMainData() {
        return mainData;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }
}
