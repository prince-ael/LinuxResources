package com.ifr_request.ael.ifrrequest;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ifr_request.ael.ifrrequest.adapters.GeneralUtility;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecieverFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView mNameLabelTextView;
    private TextView mMobileNumberLabelTextView;
    private TextView mPhotoIdLabelTextView;
    private TextView mTakaAmountLabelTextView;
    private TextView mPhotoIdNumberLabelTextView;

    private EditText mNameEditText;
    private EditText mMobileNumberEditText;
    private Spinner mPhotoIdTypeSpinner;
    private EditText mPhotoIdNumberEditText;
    private EditText mTakaAmountEditText;

    private RelativeLayout mPhotoIdNumber;



    public RecieverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View recieverFragmentView = inflater.inflate(R.layout.fragment_reciever, container, false);

        initViews(recieverFragmentView);
        initAction();

        return recieverFragmentView;
    }

    private void initViews(View recieverFragmentView) {

        mNameLabelTextView = (TextView) recieverFragmentView.findViewById(R.id.tv_reciever_name_label);
        mMobileNumberLabelTextView = (TextView) recieverFragmentView.findViewById(R.id.tv_reciever_mobile_no_label);
        mPhotoIdLabelTextView = (TextView) recieverFragmentView.findViewById(R.id.tv_reciever_photo_id_label);
        mPhotoIdLabelTextView.setVisibility(View.VISIBLE);

        mPhotoIdNumberLabelTextView = (TextView) recieverFragmentView.findViewById(R.id.tv_reciever_photoid_no_label);
        mTakaAmountLabelTextView = (TextView) recieverFragmentView.findViewById(R.id.tv_reciever_taka_amount_label);
        mPhotoIdNumber = (RelativeLayout) recieverFragmentView.findViewById(R.id.rl_photo_id_number);


        mNameEditText = (EditText) recieverFragmentView.findViewById(R.id.et_reciever_name);
        mNameEditText.setKeyListener(null);

        mMobileNumberEditText = (EditText) recieverFragmentView.findViewById(R.id.et_reciever_mobile_no);
        mMobileNumberEditText.setKeyListener(null);

        mPhotoIdTypeSpinner = (Spinner) recieverFragmentView.findViewById(R.id.spinner_photo_id_type);

        mPhotoIdNumberEditText = (EditText) recieverFragmentView.findViewById(R.id.et_reciever_photoid_no);
        mPhotoIdNumberEditText.setKeyListener(null);

        mTakaAmountEditText = (EditText) recieverFragmentView.findViewById(R.id.et_reciever_taka_amount);
        mTakaAmountEditText.setKeyListener(null);
    }

    private void initAction(){

        mPhotoIdNumberEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtility.showInputNumberDialog(R.id.et_reciever_photoid_no,
                        RecieverFragment.this ,
                        android.R.id.content);
            }
        });

        mTakaAmountEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GeneralUtility.showInputNumberDialog(R.id.et_reciever_taka_amount,
                        RecieverFragment.this ,
                        android.R.id.content);
            }
        });

        mMobileNumberEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GeneralUtility.showInputNumberDialog(R.id.et_reciever_mobile_no,
                        RecieverFragment.this ,
                        android.R.id.content);
            }
        });

        mNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtility.showInputNameDialog(R.id.et_reciever_name,
                        RecieverFragment.this,
                        android.R.id.content);
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        String value;

        if(key.equals(getString(R.string.pref_key_taka_amount))){
            value = GeneralUtility.getFormattedAmount(sharedPreferences.getString(key,""));
            mTakaAmountEditText.setText(value);
            mTakaAmountLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }else if(key.equals(getString(R.string.pref_key_mobile))){
            value = sharedPreferences.getString(key,"");
            mMobileNumberEditText.setText(value);
            mMobileNumberLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }else if(key.equals(getString(R.string.pref_key_photo_id))){
            value = sharedPreferences.getString(key,"");
            mPhotoIdNumberEditText.setText(value);
            mPhotoIdNumberLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }else if(key.equals(getString(R.string.pref_key_reciever_name))){
            value = sharedPreferences.getString(key,"");
            mNameEditText.setText(value);
            mNameLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }else if(key.equals(getString(R.string.pref_key_photo_id_pos))){

            int pos = sharedPreferences.getInt(key,-1);
            mPhotoIdTypeSpinner.setSelection((pos < 0)?0:pos);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(getString(R.string.pref_key_photo_id), mPhotoIdTypeSpinner.getSelectedItem().toString());
        edit.apply();
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RecieverFragment","in On Resumed State");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }

}
