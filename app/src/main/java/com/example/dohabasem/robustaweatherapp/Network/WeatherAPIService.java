package com.example.dohabasem.robustaweatherapp.Network;

import com.example.dohabasem.robustaweatherapp.Network.APIModels.APIResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public interface WeatherAPIService {

    //A request for getting the weather data by returning an observable
    @GET("data/2.5/forecast")
    rx.Observable<APIResponse>getCurrentWeather(@Query("q") String cityName,
                                                @Query("APPID") String AppID,
                                                @Query("cnt") Integer count);

}
