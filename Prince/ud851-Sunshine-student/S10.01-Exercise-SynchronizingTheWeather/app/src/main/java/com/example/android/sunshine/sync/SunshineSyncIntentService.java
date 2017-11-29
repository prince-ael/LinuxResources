package com.example.android.sunshine.sync;
// Completed (5) Create a new class called SunshineSyncIntentService that extends IntentService
// Completed (6) Create a constructor that calls super and passes the name of this class
// Completed (7) Override onHandleIntent, and within it, call SunshineSyncTask.syncWeather

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class SunshineSyncIntentService extends IntentService{


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public SunshineSyncIntentService() {
        super("SunshineSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
       SunshineSyncTask.syncWeather(SunshineSyncIntentService.this);
    }
}