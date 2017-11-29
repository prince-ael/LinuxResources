package com.experiment.com.vectortest;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView triangleImageView;
    private TextView myLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triangleImageView = (ImageView) findViewById(R.id.iv_triangle);
        myLog = (TextView) findViewById(R.id.myLog);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Drawable d = triangleImageView.getDrawable();

        if(d instanceof Animatable){

            ((Animatable) d).start();
            myLog.setText("Inside If\n");
        }

        myLog.append(d.getClass().getSimpleName());
    }
}
