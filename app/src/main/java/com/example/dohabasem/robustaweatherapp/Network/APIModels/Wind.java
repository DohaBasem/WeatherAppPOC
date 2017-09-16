package com.example.dohabasem.robustaweatherapp.Network.APIModels;

/**
 * Created by Doha Basem on 9/16/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    public double speed;
    @SerializedName("deg")
    @Expose
    public double deg;

}
