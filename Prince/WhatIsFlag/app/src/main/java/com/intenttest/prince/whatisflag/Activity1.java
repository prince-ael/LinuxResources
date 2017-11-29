package com.intenttest.prince.whatisflag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Activity1 extends AppCompatActivity {

    private ImageView a2Next;
    private ImageView a2Before;

    private static final String TAG ="Activity 2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        a2Next = (ImageView) findViewById(R.id.a2_next);
        a2Before = (ImageView) findViewById(R.id.a2_before);

        a2Before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Activity1.this , MainActivity.class));
            }
        });

        a2Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Activity1.this , Activity2.class));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()-Added Ao stack");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()-Removed From stack");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart()-Removed From stack");
    }
}
