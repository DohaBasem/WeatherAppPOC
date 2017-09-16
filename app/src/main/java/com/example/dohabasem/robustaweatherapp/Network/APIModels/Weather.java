package com.example.dohabasem.robustaweatherapp.Network.APIModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class Weather {
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;
}
