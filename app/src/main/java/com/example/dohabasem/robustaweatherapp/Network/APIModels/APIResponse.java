package com.example.dohabasem.robustaweatherapp.Network.APIModels;

import com.example.dohabasem.robustaweatherapp.Network.APIModels.City;
import com.example.dohabasem.robustaweatherapp.Network.APIModels.WeatherData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class APIResponse {
    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public double message;
    @SerializedName("cnt")
    @Expose
    public long cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<WeatherData> list = null;
    @SerializedName("city")
    @Expose
    public City city;

    public List<WeatherData> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }
}
