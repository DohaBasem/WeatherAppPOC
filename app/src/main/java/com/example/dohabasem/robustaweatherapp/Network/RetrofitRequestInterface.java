package com.example.dohabasem.robustaweatherapp.Network;

import com.example.dohabasem.robustaweatherapp.Utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class RetrofitRequestInterface {
    public static WeatherAPIService getWeatherAPIService(){
    WeatherAPIService weatherAPIService= new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherAPIService.class);
    return weatherAPIService;
    }
}
