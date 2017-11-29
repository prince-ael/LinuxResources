// Completed (9) Create a class called SunshineSyncUtils
    //  Completed (10) Create a public static void method called startImmediateSync
    //  Completed (11) Within that method, start the SunshineSyncIntentService
package com.example.android.sunshine.sync;

import android.content.Context;
import android.content.Intent;

public class SunshineSyncUtils{

    public static void startImmediateSync(Context context){

        context.startService(new Intent(context , SunshineSyncIntentService.class));
    }
}