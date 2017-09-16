package com.example.dohabasem.robustaweatherapp.Home.MVP_View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_View.AddEditWeatherActivity;
import com.example.dohabasem.robustaweatherapp.Home.MVP_Presenter.HomePresenter;
import com.example.dohabasem.robustaweatherapp.Home.MVP_Presenter.HomePresenterImpl;
import com.example.dohabasem.robustaweatherapp.R;
import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;
import com.example.dohabasem.robustaweatherapp.Utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {
    @BindView(R.id.weather_recycler_view)
    RecyclerView weatherRecyclerView;

    private WeatherHistoryAdapter mWeatherAdapter;
    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        weatherRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        mPresenter=new HomePresenterImpl(this,getApplicationContext());
        mWeatherAdapter=new WeatherHistoryAdapter(new ArrayList<WeatherItem>(),getApplicationContext(),mPresenter);
        weatherRecyclerView.setAdapter(mWeatherAdapter);
        mPresenter.getAllWeatherItems();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mPresenter.onAddNewWeatherItemClicked();
            }
        });
    }

    @Override
    public void showAllWeatherItems(ArrayList<WeatherItem>weatherItems) {
        mWeatherAdapter.setWeatherList(weatherItems);
    }

    @Override
    public void navigateToWeatherItem(WeatherItem weatherItem) {
        Intent editIntent=new Intent(this, AddEditWeatherActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constants.WEATHER_ITEM_SERIALIZABLE,weatherItem);
        editIntent.putExtras(bundle);
        startActivity(editIntent);
    }

    @Override
    public void navigateToNewWeatherItem() {
        Intent editIntent=new Intent(this, AddEditWeatherActivity.class);
        startActivity(editIntent);
    }
}
