package com.example.dohabasem.robustaweatherapp.Home.MVP_Presenter;

import android.content.Context;

import com.example.dohabasem.robustaweatherapp.Home.MVP_View.HomeView;
import com.example.dohabasem.robustaweatherapp.Sqlite.DatabaseHelper;
import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;

import java.util.ArrayList;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class HomePresenterImpl implements HomePresenter {
    Context mContext;
    DatabaseHelper mDBHelper;
    ArrayList<WeatherItem> mWeatherItems;
    HomeView mView;

    public HomePresenterImpl(HomeView homeView, Context context) {
        mDBHelper = new DatabaseHelper(context);
        mView = homeView;
    }

    @Override
    public void getAllWeatherItems() {
        mWeatherItems = (ArrayList<WeatherItem>) mDBHelper.getAllSavedItems();
        mView.showAllWeatherItems(mWeatherItems);

    }

    @Override
    public void onItemClicked(WeatherItem weatherItem) {
        mView.navigateToWeatherItem(weatherItem);
    }

    @Override
    public void onAddNewWeatherItemClicked() {
        mView.navigateToNewWeatherItem();
    }
}
