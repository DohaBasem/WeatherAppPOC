package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;

/**
 * Created by Doha Basem on 9/15/2017.
 */

public interface AddEditPresenter extends AddEditViewClickListener,ResponseReturnListener {
    void setCurrentItem(WeatherItem item);

    void addCapturedImage(Bitmap imageBitmap, Context context);

}
