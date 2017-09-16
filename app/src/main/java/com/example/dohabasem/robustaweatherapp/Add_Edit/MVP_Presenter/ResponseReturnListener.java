package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Presenter;

import com.example.dohabasem.robustaweatherapp.Network.APIModels.APIResponse;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public interface ResponseReturnListener {

    void onWeatherResponseReturned(APIResponse apiResponse,String requestedItemTag);
}
