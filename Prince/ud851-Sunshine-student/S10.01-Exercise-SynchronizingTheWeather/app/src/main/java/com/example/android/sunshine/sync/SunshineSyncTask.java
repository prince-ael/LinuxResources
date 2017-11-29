//  Completed (1) Create a class called SunshineSyncTask
//  Completed (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      Completed (3) Within syncWeather, fetch new weather data
//      Completed (4) If we have valid results, delete the old data and insert the new
package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SunshineSyncTask{

    synchronized public static void syncWeather(Context context){

        try {

            URL weatheUrl = NetworkUtils.getUrl(context);
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(weatheUrl);

            if(jsonResponse != null && jsonResponse.length() > 0){

                ContentValues[] values = OpenWeatherJsonUtils
                        .getWeatherContentValuesFromJson(context , jsonResponse);
                ContentResolver resolver = context.getContentResolver();
                int rowsDeleted = resolver.delete(WeatherContract.WeatherEntry.CONTENT_URI , null ,null);

                int rowsInserted = resolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI , values);

                Log.d("SunshineSyncTask" , rowsDeleted+" Rows Deleted");
                Log.d("SunshineSyncTask" , rowsInserted+" Rows Inserted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}