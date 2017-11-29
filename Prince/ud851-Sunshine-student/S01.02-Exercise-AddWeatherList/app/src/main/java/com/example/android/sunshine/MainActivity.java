/*
 * Copyright (C) 2016 The Android Open Source Project
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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO (1) Create a field to store the weather display TextView

    private TextView weatherDispTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // TODO (2) Use findViewById to get a reference to the weather display TextView

        weatherDispTv = (TextView) findViewById(R.id.tv_weather_data);

        // TODO (3) Create an array of Strings that contain fake weather data

        String[] fakeArr = {"Temp : 20.35\nWind Velocity : 35.5",
                "Temp : 21.45\nWind Velocity : 36.5",
                "Temp : 22.36\nWind Velocity : 37.6",
                "Temp : 23.37\nWind Velocity : 38.7",
                "Temp : 24.38\nWind Velocity : 39.8",
                "Temp : 25.39\nWind Velocity : 41.9",
                "Temp : 26.39\nWind Velocity : 42.9",
                "Temp : 27.39\nWind Velocity : 43.9",
                "Temp : 28.39\nWind Velocity : 44.9",
                "Temp : 29.39\nWind Velocity : 45.9",
                "Temp : 30.39\nWind Velocity : 46.9"};

        // TODO (4) Append each String from the fake weather data array to the TextView

        for(String fake : fakeArr){

            weatherDispTv.append(fake + "\n\n\n");
        }
    }
}