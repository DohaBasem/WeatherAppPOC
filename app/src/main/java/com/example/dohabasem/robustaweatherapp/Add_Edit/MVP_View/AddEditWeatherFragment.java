package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_Presenter.AddEditPresenter;
import com.example.dohabasem.robustaweatherapp.R;
import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;
import com.example.dohabasem.robustaweatherapp.Utils.Constants;
import com.example.dohabasem.robustaweatherapp.Utils.GlobalFunctions;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.example.dohabasem.robustaweatherapp.Utils.Constants.REQUEST_IMAGE_CAPTURE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEditWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEditWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditWeatherFragment extends Fragment implements AddEditView {

    @BindView(R.id.add_image_fab_button)
    FloatingActionButton addImageFab;
    @BindView(R.id.weather_image)
    ImageView weatherImageView;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.temp)
    TextView tempTextView;
    @BindView(R.id.humidity)
    TextView humidityTextView;

    private AddEditPresenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private WeatherItem weatherItem;

    private Bitmap imageBitmap;

    public AddEditWeatherFragment() {

    }


    public static AddEditWeatherFragment newInstance() {
        AddEditWeatherFragment fragment = new AddEditWeatherFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((WeatherItem) getArguments().getSerializable(Constants.WEATHER_ITEM_SERIALIZABLE) != null) {
            weatherItem = (WeatherItem) getArguments().getSerializable(Constants.WEATHER_ITEM_SERIALIZABLE);

        } else weatherItem = new WeatherItem();
        mPresenter.setCurrentItem(weatherItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_add_edit_weather, container, false);
        ButterKnife.bind(this, inflatedView);
        if (weatherItem.getImagePath() != null)
            Picasso.with(getContext()).load(weatherItem.getImagePath()).into(weatherImageView);
        if (weatherItem.getTemperature() != null)
            tempTextView.setText("Temperature  " + weatherItem.getTemperature());
        if (weatherItem.getHumidity() != null)
            humidityTextView.setText("Humidity  " + weatherItem.getHumidity());
        disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();

        MenuItem shareToFB = menu.getItem(0);
        MenuItem saveItem = menu.getItem(1);
        MenuItem addTemp = menu.getItem(2);
        MenuItem addHumidity = menu.getItem(3);

        shareToFB.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (imageBitmap != null) {
                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(imageBitmap)
                            .build();
                    ShareContent shareContent = new ShareMediaContent.Builder().addMedium(photo).build();
                    ShareDialog shareDialog = new ShareDialog(getActivity());
                    shareDialog.show(shareContent, ShareDialog.Mode.AUTOMATIC);
                } else Toast.makeText(getContext(), "No Image is Loaded", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        addTemp.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mPresenter.onAddTempClicked();
                return true;
            }
        });
        saveItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mPresenter.onSaveItemClicked();
                return true;
            }
        });
        addHumidity.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mPresenter.onAddHumidityClicked();
                return true;
            }
        });
        addImageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onAddPhotoClicked();
            }
        });
        return inflatedView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            weatherImageView.setImageBitmap(imageBitmap);
            boolean grant = GlobalFunctions.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.REQUEST_IMAGE_SAVE, getActivity());
            if (grant)
                mPresenter.addCapturedImage(imageBitmap, getContext());

        }
    }


    @Override
    public void addEditPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void addEditPlaceName() {

    }

    @Override
    public void addEditTemperature() {

    }

    @Override
    public void displayTemp(String temp) {
        tempTextView.setText("Temperature " + temp + "  °F");
        Toast.makeText(getContext(), temp, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayHumidity(String humidity) {
        humidityTextView.setText("Humidity " + humidity);
        Toast.makeText(getContext(), humidity, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showItemSavedToast() {
        Toast.makeText(getContext(), "Item Saved ", Toast.LENGTH_LONG).show();
    }

    public void createPresenter(AddEditPresenter presenter) {
        mPresenter = presenter;
    }

    //Function to force icons and their titles be displayed in the bottom navigation
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            //Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            //Timber.e(e, "Unable to change value of shift mode");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
