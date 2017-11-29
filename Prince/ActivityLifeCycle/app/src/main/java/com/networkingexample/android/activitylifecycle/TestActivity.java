package com.networkingexample.android.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private TextView landingMessageTV;
    private String welcomeMsg = "LANDING SUCCESSFUL";

    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent recievedIntent = getIntent();

        if(recievedIntent.getExtras().containsKey(MainActivity.MSG_KEY)){

            welcomeMsg += "\nExtra Message : "+recievedIntent.getExtras().getString(MainActivity.MSG_KEY);
        }

        landingMessageTV = (TextView) findViewById(R.id.tv_landing_message);
        landingMessageTV.setText(welcomeMsg);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG , "in onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG , "in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG , "in onDestroy");
    }
}
