package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Model;

import android.util.Log;

import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Presenter.AddEditPresenter;
import com.example.dohabasem.robustaweatherapp.Network.APIModels.APIResponse;
import com.example.dohabasem.robustaweatherapp.Network.RetrofitRequestInterface;
import com.example.dohabasem.robustaweatherapp.Network.WeatherAPIService;
import com.example.dohabasem.robustaweatherapp.Utils.Constants;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Doha Basem on 9/15/2017.
 */

public class AddEditModelImp implements AddEditModel{
    WeatherAPIService apiService;
    AddEditPresenter mPresenter;

    public  AddEditModelImp(AddEditPresenter presenter){
        mPresenter=presenter;
    }

    @Override
    public void getCurrentWeather(final String itemRequestTag) {
        apiService=RetrofitRequestInterface.getWeatherAPIService();
        apiService.getCurrentWeather(Constants.CURRENT_CITY,Constants.WEATHERMAP_APP_ID,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<APIResponse>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("OnError",e.getMessage());
                    }

                    @Override
                    public void onNext(APIResponse apiResponse) {
                        Log.d("API RESPONSE OnNext ", apiResponse.toString());
                        mPresenter.onWeatherResponseReturned(apiResponse,itemRequestTag);
                    }
                })
        ;
    }
}
