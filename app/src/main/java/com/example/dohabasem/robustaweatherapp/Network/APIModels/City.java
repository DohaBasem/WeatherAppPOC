package com.example.dohabasem.robustaweatherapp.Network.APIModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class City{
        @SerializedName("id")
        @Expose
        public long id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("coord")
        @Expose
        public Coord coord;
        @SerializedName("country")
        @Expose
        public String country;

    public class Coord {

        @SerializedName("lat")
        @Expose
        public double lat;
        @SerializedName("lon")
        @Expose
        public double lon;

    }
}
