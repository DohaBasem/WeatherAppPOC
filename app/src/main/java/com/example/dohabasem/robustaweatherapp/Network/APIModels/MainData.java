package com.example.dohabasem.robustaweatherapp.Network.APIModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class MainData {

    @SerializedName("temp")
    @Expose
    public double temp;
    @SerializedName("temp_min")
    @Expose
    public double tempMin;
    @SerializedName("temp_max")
    @Expose
    public double tempMax;
    @SerializedName("pressure")
    @Expose
    public double pressure;
    @SerializedName("sea_level")
    @Expose
    public double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    public double grndLevel;
    @SerializedName("humidity")
    @Expose
    public long humidity;
    @SerializedName("temp_kf")
    @Expose
    public double tempKf;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public long getHumidity() {
        return humidity;
    }
}
