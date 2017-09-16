package com.example.dohabasem.robustaweatherapp.Network.APIModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class Clouds {
    @SerializedName("all")
    @Expose
    public long all;
}
