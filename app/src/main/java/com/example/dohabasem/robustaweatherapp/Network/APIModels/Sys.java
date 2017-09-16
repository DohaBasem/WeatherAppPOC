package com.example.dohabasem.robustaweatherapp.Network.APIModels;

/**
 * Created by Doha Basem on 9/16/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("pod")
    @Expose
    public String pod;

}