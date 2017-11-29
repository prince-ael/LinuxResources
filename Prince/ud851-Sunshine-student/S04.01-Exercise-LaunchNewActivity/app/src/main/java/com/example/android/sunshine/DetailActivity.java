package com.example.android.sunshine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mWeatherDisplayTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle args = getIntent().getExtras();

        String weatherForDay = (args.containsKey(MainActivity.MAINACTIVITY_EXTRA))?args.getString(MainActivity.MAINACTIVITY_EXTRA) : "";
        mWeatherDisplayTextView = (TextView) findViewById(R.id.tv_weather_data_display);
        mWeatherDisplayTextView.setText(weatherForDay);

    }
}
