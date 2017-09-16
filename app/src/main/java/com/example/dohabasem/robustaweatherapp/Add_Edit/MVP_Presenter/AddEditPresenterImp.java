package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_View.AddEditView;
import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Model.AddEditModel;
import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Model.AddEditModelImp;
import com.example.dohabasem.robustaweatherapp.Network.APIModels.APIResponse;
import com.example.dohabasem.robustaweatherapp.Network.APIModels.MainData;
import com.example.dohabasem.robustaweatherapp.Sqlite.DatabaseHelper;
import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;
import com.example.dohabasem.robustaweatherapp.Utils.Constants;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Doha Basem on 9/15/2017.
 */

public class AddEditPresenterImp implements AddEditPresenter {

    DatabaseHelper mDatabaseHelper;
    AddEditView mView;
    AddEditModel mModel;
    Context mContext;

    WeatherItem weatherItem;
    APIResponse weatherAPIResponse;
    MainData mainData;

    public AddEditPresenterImp(AddEditView view,Context context) {
        mView = view;
        mModel = new AddEditModelImp(this);
        mContext=context;
        mDatabaseHelper=new DatabaseHelper(context);
    }

    @Override
    public void onAddPhotoClicked() {
        mView.addEditPhoto();
    }

    //gets Data from weather API then adds the current temp to db and views it on UI
    @Override
    public void onAddTempClicked() {
        if (weatherAPIResponse == null)
            mModel.getCurrentWeather(Constants.REQUEST_TEMP);
        else
            addCurrentTemp();
    }

    @Override
    public void onAddHumidityClicked() {
        if (weatherAPIResponse == null)
            mModel.getCurrentWeather(Constants.REQUEST_HUMID);
        else
            addCurrentHumidity();
    }

    @Override
    public void onSaveItemClicked() {
        mDatabaseHelper.addWeatherItem(weatherItem);
        ArrayList<WeatherItem> weatherItems=(ArrayList<WeatherItem>) mDatabaseHelper.getAllSavedItems();
        Log.d("weather","allItems");
    }

    @Override
    public void setCurrentItem(WeatherItem item) {
        this.weatherItem = item;
    }

    @Override
    public void addCapturedImage(Bitmap imageBitmap, Context context) {
        Uri imageUri = getImageUri(context, imageBitmap);
        weatherItem.setImagePath(imageUri.toString());

    }

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,((Long)System.currentTimeMillis()).toString(), null);
        return Uri.parse(path);
    }


    @Override
    public void onWeatherResponseReturned(APIResponse apiResponse, String requestedItem) {
        this.weatherAPIResponse = apiResponse;
        switch (requestedItem) {
            case (Constants.REQUEST_TEMP):
                addCurrentTemp();
                break;
            case(Constants.REQUEST_HUMID):
                addCurrentHumidity();
                break;


        }
    }

    private void addCurrentTemp() {
        MainData mainData = getMainData();
        Double currentTemp = mainData.getTemp();
        weatherItem.setTemperature(currentTemp.toString());
        mView.displayTemp(currentTemp.toString());
    }
    private void addCurrentHumidity(){
        MainData mainData = getMainData();
        Long currentHumidity=mainData.getHumidity();
        weatherItem.setHumidity(currentHumidity.toString());
        mView.displayHumidity(currentHumidity.toString());

    }

    private MainData getMainData() {
        mainData = weatherAPIResponse.getList().get(0).getMainData();
        return mainData;
    }
}
