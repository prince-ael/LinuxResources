package com.ael_mnisiddique.com.textviewtest;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mPassWrapper;
    private EditText mPassEditText;
    private TextView mUmrErrorView;

    private TextView mInputMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPassEditText = (EditText) findViewById(R.id.et_password);
        mUmrErrorView = (TextView) findViewById(R.id.tv_umr_error_view);
        mInputMethod = (TextView) findViewById(R.id.tv_default);

        mInputMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String d = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
            }
        });

        mPassEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_DONE){

                    mUmrErrorView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
    }
}
