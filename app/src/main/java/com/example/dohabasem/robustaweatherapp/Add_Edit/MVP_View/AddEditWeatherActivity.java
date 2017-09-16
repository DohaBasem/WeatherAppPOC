package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_View;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Presenter.AddEditPresenterImp;
import com.example.dohabasem.robustaweatherapp.R;
import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;
import com.example.dohabasem.robustaweatherapp.Utils.Constants;

public class AddEditWeatherActivity extends AppCompatActivity implements AddEditWeatherFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_weather);
        Intent editIntent=getIntent();
        Bundle bundle=editIntent.getExtras();
        AddEditWeatherFragment fragment=findOrCreateWeatherFragment(R.id.add_edit_fragment_layout);
        if(bundle!=null)
            fragment.setArguments(bundle);
        fragment.createPresenter(new AddEditPresenterImp(fragment,getApplicationContext()));


    }

    private AddEditWeatherFragment findOrCreateWeatherFragment(int fragmentID) {

        AddEditWeatherFragment addEditWeatherFragment = (AddEditWeatherFragment) this.getSupportFragmentManager().findFragmentById(fragmentID);
        if (addEditWeatherFragment == null) {
            addEditWeatherFragment = AddEditWeatherFragment.newInstance();
            FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(fragmentID, addEditWeatherFragment);
            fragmentTransaction.commit();
        }

        return addEditWeatherFragment;

    }

    @Override
    public void onBackPressed() {
        setResult(Constants.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
