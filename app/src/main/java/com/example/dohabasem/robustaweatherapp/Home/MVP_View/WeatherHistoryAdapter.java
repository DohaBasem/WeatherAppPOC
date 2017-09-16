package com.example.dohabasem.robustaweatherapp.Home.MVP_View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dohabasem.robustaweatherapp.Home.MVP_Presenter.ItemClickListener;
import com.example.dohabasem.robustaweatherapp.R;
import com.example.dohabasem.robustaweatherapp.Sqlite.WeatherItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class WeatherHistoryAdapter extends RecyclerView.Adapter<WeatherHistoryAdapter.WeatherItemViewHolder> {

    ArrayList<WeatherItem> mWeatherItems;
    Context mContext;
    ItemClickListener mListener;

    public WeatherHistoryAdapter(ArrayList<WeatherItem> items, Context context, ItemClickListener listener) {
        mWeatherItems = items;
        mContext = context;
        mListener=listener;

    }

    @Override
    public WeatherItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_in_grid, parent, false);
        WeatherItemViewHolder viewHolder = new WeatherItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherItemViewHolder holder, int position) {
        final WeatherItem weatherItem = mWeatherItems.get(position);
        if (weatherItem.getImagePath() != null)
            Picasso.with(mContext).load(weatherItem.getImagePath()).fit().into(holder.gridImageView);
        else
            Picasso.with(mContext).load(R.drawable.default_wether).fit().into(holder.gridImageView);
        holder.gridImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(weatherItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mWeatherItems.size();
    }

    public void setWeatherList(ArrayList<WeatherItem> weatherItems) {
        this.mWeatherItems.addAll(weatherItems);
        notifyDataSetChanged();
    }

    public class WeatherItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_in_grid)
        ImageView gridImageView;

        public WeatherItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
