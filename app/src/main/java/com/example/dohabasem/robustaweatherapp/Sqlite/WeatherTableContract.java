package com.example.dohabasem.robustaweatherapp.Sqlite;

import android.provider.BaseColumns;

/**
 * Created by Doha Basem on 9/15/2017.
 */

public final class WeatherTableContract {

    public static String TABLE_NAME="weather_table";

    public static String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+
                                        "("+WeatherTableColumns.ITEM_ID+"  INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                            WeatherTableColumns.ITEM_PLACE+" TEXT ,"+
                                            WeatherTableColumns.ITEM_PHOTO+" TEXT ,"+
                                            WeatherTableColumns.ITEM_TEMP+" TEXT ,"+
                                            WeatherTableColumns.ITEM_HUMIDITY +" TEXT )";

    public static abstract class WeatherTableColumns implements BaseColumns {

        public static final String ITEM_ID = "item_id";

        public static final String ITEM_PLACE="item_place";

        public static final String ITEM_PHOTO="item_photo";

        public static final String ITEM_TEMP="item_temp";

        public static final String ITEM_HUMIDITY ="weather_condition";


    }

}
