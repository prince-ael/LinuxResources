/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.SunshineDateUtils;
import com.example.android.sunshine.utilities.SunshineWeatherUtils;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
//      Completed (21) Implement LoaderManager.LoaderCallbacks<Cursor>

    /*
     * In this Activity, you can share the selected day's forecast. No social sharing is complete
     * without using a hashtag. #BeTogetherNotTheSame
     */
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

//  Completed (18) Create a String array containing the names of the desired data columns from our ContentProvider
//  Completed (19) Create constant int values representing each column name's position above
//  Completed (20) Create a constant int to identify our loader used in DetailActivity

    private static final String[] DETAIL_FORECAST_PROJECTION = {WeatherContract.WeatherEntry.COLUMN_WEATHER_ID,
            WeatherContract.WeatherEntry.COLUMN_DATE,
            WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,
            WeatherContract.WeatherEntry.COLUMN_MIN_TEMP,
            WeatherContract.WeatherEntry.COLUMN_HUMIDITY,
            WeatherContract.WeatherEntry.COLUMN_WIND_SPEED,
            WeatherContract.WeatherEntry.COLUMN_PRESSURE,
            WeatherContract.WeatherEntry.COLUMN_DEGREES};

    public static final int INDEX_WEATHER_ID = 0;
    public static final int INDEX_DATE       = 1;
    public static final int INDEX_MAX_TEMP   = 2;
    public static final int INDEX_MIN_TEMP   = 3;
    public static final int INDEX_HUMIDITY   = 4;
    public static final int INDEX_WIND_SPEED = 5;
    public static final int INDEX_PRESSURE   = 6;
    public static final int INDEX_WIND_DIRECTION   = 7;

    private static final int ID_FORECAST_DETAIL_LOADER = 55;

    /* A summary of the forecast that can be shared by clicking the share button in the ActionBar */
    private String mForecastSummary;

//  Completed(15) Declare a private Uri field called

    private Uri mUri;

//  Completed (10) Remove the mWeatherDisplay TextView declaration


//  Completed (11) Declare TextViews for the date, description, high, low, humidity, wind, and pressure

    private TextView mDateDisplayTextView;
    private TextView mDescriptionTextView;
    private TextView mHighTempTextView;
    private TextView mLowTempTextView;
    private TextView mHumidityTextView;
    private TextView mWindSpeedTextView;
    private TextView mPressureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//      Completed (12) Remove mWeatherDisplay TextView

//      Completed (13) Find each of the TextViews by ID

        mDateDisplayTextView = (TextView) findViewById(R.id.tv_selected_days_date);
        mDescriptionTextView = (TextView) findViewById(R.id.tv_selected_weather_desc);
        mHighTempTextView = (TextView) findViewById(R.id.tv_selected_days_high_temperature);
        mLowTempTextView = (TextView) findViewById(R.id.tv_selected_days_low_temperature);
        mHumidityTextView = (TextView) findViewById(R.id.tv_selected_days_humidity);
        mWindSpeedTextView = (TextView) findViewById(R.id.tv_selected_days_wind);
        mPressureTextView = (TextView) findViewById(R.id.tv_selected_days_pressure);

//      Completed (14) Remove the code that checks for extra text
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {

            mUri = intentThatStartedThisActivity.getData();

            if(mUri == null){
                throw new NullPointerException("Know Uri Found");
            }
        }
//      Completed (16) Use getData to get a reference to the URI passed with this Activity's Intent
//      Completed (17) Throw a NullPointerException if that URI is null
//      Completed (35) Initialize the loader for DetailActivity

        getSupportLoaderManager().initLoader(ID_FORECAST_DETAIL_LOADER,
                null,
                this);

    }

    /**
     * This is where we inflate and set up the menu for this Activity.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     *         if you return false it will not be shown.
     *
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.detail, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    /**
     * Callback invoked when a menu item was selected from this Activity's menu. Android will
     * automatically handle clicks on the "up" button for us so long as we have specified
     * DetailActivity's parent Activity in the AndroidManifest.
     *
     * @param item The menu item that was selected by the user
     *
     * @return true if you handle the menu click here, false otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Get the ID of the clicked item */
        int id = item.getItemId();

        /* Settings menu item clicked */
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        /* Share menu item clicked */
        if (id == R.id.action_share) {
            Intent shareIntent = createShareForecastIntent();
            startActivity(shareIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Uses the ShareCompat Intent builder to create our Forecast intent for sharing.  All we need
     * to do is set the type, text and the NEW_DOCUMENT flag so it treats our share as a new task.
     * See: http://developer.android.com/guide/components/tasks-and-back-stack.html for more info.
     *
     * @return the Intent to use to share our weather forecast
     */
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mForecastSummary + FORECAST_SHARE_HASHTAG)
                .getIntent();
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        return shareIntent;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        CursorLoader forecastDetailLoader;

        if(id == ID_FORECAST_DETAIL_LOADER){
            forecastDetailLoader = new CursorLoader(this,
                    mUri,
                    DETAIL_FORECAST_PROJECTION,
                    null,
                    null,
                    null);
            return forecastDetailLoader;
        }

        throw new RuntimeException("Unknown Loader Requested With Id "+id);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data == null){
            return;
        }

        data.moveToFirst();
        long date = data.getLong(data.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE));
        String dateString = SunshineDateUtils.getFriendlyDateString(this , date , true);



        double high = data.getDouble(INDEX_MAX_TEMP);
        double low = data.getDouble(INDEX_MIN_TEMP);
        double humidity = data.getDouble(INDEX_HUMIDITY);
        double pressure = data.getDouble(INDEX_PRESSURE);
        float windspeed = data.getFloat(INDEX_WIND_SPEED);
        float direction = data.getFloat(INDEX_WIND_DIRECTION);


        int weatherID = data.getInt(INDEX_WEATHER_ID);

        mDateDisplayTextView.setText(dateString);
        mHighTempTextView.setText(SunshineWeatherUtils.formatTemperature(this , high));
        mLowTempTextView.setText(SunshineWeatherUtils.formatTemperature(this , low));
        mHumidityTextView.setText(getString(R.string.format_humidity , humidity));
        mPressureTextView.setText(getString(R.string.format_pressure , pressure));
        mWindSpeedTextView.setText(SunshineWeatherUtils.getFormattedWind(this , windspeed , direction));
        String description = SunshineWeatherUtils.getStringForWeatherCondition(this , weatherID);
        mDescriptionTextView.setText(description);

        mForecastSummary = String.format(String.format("%s - %s - %1.2f/%1.2f",
                dateString, description, high, low));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

//  Completed (22) Override onCreateLoader
//          Completed (23) If the loader requested is our detail loader, return the appropriate CursorLoader

//  Completed (24) Override onLoadFinished
//      Completed (25) Check before doing anything that the Cursor has valid data
//      Completed (26) Display a readable data string
//      Completed (27) Display the weather description (using SunshineWeatherUtils)
//      Completed (28) Display the high temperature
//      Completed (29) Display the low temperature
//      Completed (30) Display the humidity
//      Completed (31) Display the wind speed and direction
//      Completed (32) Display the pressure
//      Completed (33) Store a forecast summary in mForecastSummary


//  Completed (34) Override onLoaderReset, but don't do anything in it yet

}