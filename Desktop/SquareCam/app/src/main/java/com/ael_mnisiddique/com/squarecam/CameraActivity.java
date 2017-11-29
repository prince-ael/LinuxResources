package com.ael_mnisiddique.com.squarecam;

import android.Manifest;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity implements FragmentListener{

    private static final int REQUEST_STORAGE_PERMISSION = 299;
    private ActivityListener mActivityListener;
    private Button mCaptureButton;

    private ProgressBar mCaptureIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        CameraWindowFragment mCameraWindow = new CameraWindowFragment();

        mActivityListener = mCameraWindow;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container , mCameraWindow)
                .commit();

        mCaptureButton = (Button) findViewById(R.id.btn_capture);
        mCaptureIndicator = (ProgressBar) findViewById(R.id.pb_capture_indicator);


        mCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mActivityListener.onCaptureButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityListener.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityListener.stopCamera();
    }

    @Override
    public void onSaveFinished() {
        mCaptureIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStartSaving() {
        mCaptureIndicator.setVisibility(View.VISIBLE);
        mCaptureIndicator.getIndeterminateDrawable().setColorFilter(Color.WHITE , PorterDuff.Mode.MULTIPLY);
    }

//    private void requestFileWritePermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
////            new ConfirmationDialog().show(getChildFragmentManager(), FRAGMENT_DIALOG);
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    REQUEST_STORAGE_PERMISSION);
//        }
//    }
}
