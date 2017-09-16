package com.example.dohabasem.robustaweatherapp.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doha Basem on 9/15/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="weather_database";

    private static final String WEATHER_ITEMS_TABLE="weather_table";

    private static final int DB_VERSION=1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(WeatherTableContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherTableContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addWeatherItem(WeatherItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeatherTableContract.WeatherTableColumns.ITEM_PHOTO,item.getImagePath());
        values.put(WeatherTableContract.WeatherTableColumns.ITEM_PLACE,item.getPlace());
        values.put(WeatherTableContract.WeatherTableColumns.ITEM_TEMP,item.getTemperature());
        values.put(WeatherTableContract.WeatherTableColumns.ITEM_HUMIDITY,item.getHumidity());
        db.insert(WeatherTableContract.TABLE_NAME,null,values);
        Log.d("dbHelper","Item Saved");
        db.close();


    }

    public List<WeatherItem> getAllSavedItems(){
        List<WeatherItem> weatherItemList=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + WeatherTableContract.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                WeatherItem weatherItem=new WeatherItem();
                weatherItem.setItemId(cursor.getInt(0));
                weatherItem.setPlace(cursor.getString(1));
                weatherItem.setImagePath(cursor.getString(2));
                weatherItem.setTemperature(cursor.getString(3));
                weatherItem.setHumidity(cursor.getString(4));
                // Adding contact to list
                weatherItemList.add(weatherItem);
            } while (cursor.moveToNext());
        }


        return weatherItemList;

    }
}
