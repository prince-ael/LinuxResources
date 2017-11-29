package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by adaptive on 6/12/17.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{

    private String[] mWeatherData;

    public ForecastAdapter() {
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ForecastAdapterViewHolder faViewHolder;
        Context mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.forecast_list , parent , false);
        faViewHolder = new ForecastAdapterViewHolder(itemView);
        return faViewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {

        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        int len = (mWeatherData == null)? 0 : mWeatherData.length;
        return (len > 0)? len : 0;
    }

    public void setWeatherData(String[] mWeatherData){

        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }


    protected class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View itemView) {
            super(itemView);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }
    }
}
