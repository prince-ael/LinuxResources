package com.ifr_request.ael.ifrrequest;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifr_request.ael.ifrrequest.adapters.GeneralUtility;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends DialogFragment implements View.OnClickListener,SharedPreferences.OnSharedPreferenceChangeListener{
    
    private TextView textViewAsCancelButton;
    private TextView textViewAsSubmitButton;

    private TextView mNameTextView;
    private TextView mSenderNameTextView;
    private TextView mPinTextView;
    private TextView mTtNoTextView;
    private TextView mExchangeHouseTextView;
    private TextView mSenderCountryTextView;
    private TextView mTakaAmountTextView;
    private TextView mMobileNoTextView;
    private TextView mNIDTextView;

    private RelativeLayout mSenderNameRelativeLayout;
    private RelativeLayout mNameRelativeLayout;
    private RelativeLayout mMobileNoRelativeLayout;
    private RelativeLayout mNidNoRelativeLayout;
    private RelativeLayout mTakaAmountRelativeLayout;

    
    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View reviewDialogView = inflater.inflate(R.layout.fragment_review, container, false);
        initView(reviewDialogView);
        initData();

        return reviewDialogView;
    }


    private void initView(View reviewDialogView) {

        textViewAsCancelButton = (TextView) reviewDialogView.findViewById(R.id.tv_review_cancel_btn);
        textViewAsSubmitButton = (TextView) reviewDialogView.findViewById(R.id.tv_review_submit_btn);

        mNameTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_name_data);
        mSenderNameTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_sender_name_data);
        mSenderCountryTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_sender_country_data);
        mExchangeHouseTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_exchange_house_data);
        mTakaAmountTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_taka_amount_data);
        mMobileNoTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_mobile_no_data);
        mNIDTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_nid_no_data);
        mPinTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_pin_data);
        mTtNoTextView = (TextView) reviewDialogView.findViewById(R.id.tv_review_ttno_data);

        mNameRelativeLayout = (RelativeLayout) reviewDialogView.findViewById(R.id.rl_review_name);
        mSenderNameRelativeLayout = (RelativeLayout) reviewDialogView.findViewById(R.id.rl_review_sender_name);
        mTakaAmountRelativeLayout = (RelativeLayout) reviewDialogView.findViewById(R.id.rl_review_taka_amount);
        mMobileNoRelativeLayout = (RelativeLayout) reviewDialogView.findViewById(R.id.rl_review_mobile_no);
        mNidNoRelativeLayout = (RelativeLayout) reviewDialogView.findViewById(R.id.rl_review_nid_no);


        //----------Setting Click Listener-------------------------------

        textViewAsCancelButton.setOnClickListener(this);
        textViewAsSubmitButton.setOnClickListener(this);
        mNameRelativeLayout.setOnClickListener(this);
        mSenderNameRelativeLayout.setOnClickListener(this);
        mTakaAmountRelativeLayout.setOnClickListener(this);
        mMobileNoRelativeLayout.setOnClickListener(this);
        mNidNoRelativeLayout.setOnClickListener(this);

        mPinTextView.setOnClickListener(this);
        mTtNoTextView.setOnClickListener(this);
    }

    private void initData(){

        SharedPreferences ifrPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mNameTextView.setText(ifrPreference.getString(getString(R.string.pref_key_reciever_name),""));
        mSenderNameTextView.setText(ifrPreference.getString(getString(R.string.pref_key_sender_name),""));
        mSenderCountryTextView.setText(ifrPreference.getString(getString(R.string.pref_key_sender_country),""));
        mExchangeHouseTextView.setText(ifrPreference.getString(getString(R.string.pref_key_exchange_house),""));
        mNIDTextView.setText(ifrPreference.getString(getString(R.string.pref_key_photo_id),""));
        mTtNoTextView.setText(ifrPreference.getString(getString(R.string.pref_key_tt),""));
        mTakaAmountTextView.setText(GeneralUtility.getFormattedAmount(ifrPreference.getString(getString(R.string.pref_key_taka_amount),"")));
        mPinTextView.setText(ifrPreference.getString(getString(R.string.pref_key_pin),""));
        mMobileNoTextView.setText(ifrPreference.getString(getString(R.string.pref_key_mobile),""));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog reviewDialog = super.onCreateDialog(savedInstanceState);
        reviewDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return reviewDialog;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.tv_review_cancel_btn : dismiss();break;
            case R.id.tv_review_submit_btn : dismiss();break;
            case R.id.rl_review_name : GeneralUtility.showInputNumberDialog(R.id.tv_review_name_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;
            case R.id.rl_review_mobile_no : GeneralUtility.showInputNumberDialog(R.id.tv_review_mobile_no_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;
            case R.id.rl_review_taka_amount : GeneralUtility.showInputNumberDialog(R.id.tv_review_taka_amount_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;
            case R.id.rl_review_nid_no : GeneralUtility.showInputNumberDialog(R.id.tv_review_nid_no_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;
            case R.id.tv_review_pin_data : GeneralUtility.showInputNumberDialog(R.id.tv_review_pin_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;
            case R.id.rl_review_sender_name : GeneralUtility.showInputNumberDialog(R.id.tv_review_sender_name_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;
            case R.id.tv_review_ttno_data : GeneralUtility.showInputNumberDialog(R.id.tv_review_ttno_data,
                    ReviewFragment.this,
                    android.R.id.content);
                break;

        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals(getString(R.string.pref_key_taka_amount))){
            mTakaAmountTextView.setText(GeneralUtility.getFormattedAmount(sharedPreferences.getString(key,"")));
        }else if(key.equals(getString(R.string.pref_key_mobile))){
            mMobileNoTextView.setText(sharedPreferences.getString(key,""));
        }else if(key.equals(getString(R.string.pref_key_photo_id))){
            mNIDTextView.setText(sharedPreferences.getString(key,""));
        }else if(key.equals(getString(R.string.pref_key_reciever_name))){
            mNameTextView.setText(sharedPreferences.getString(key,""));
        }else if(key.equals(getString(R.string.pref_key_sender_name))){
            mSenderNameTextView.setText(sharedPreferences.getString(key,""));
        }else if(key.equals(getString(R.string.pref_key_pin))){
            mPinTextView.setText(sharedPreferences.getString(key,""));
        }else if(key.equals(getString(R.string.pref_key_tt))){
            mTtNoTextView.setText(sharedPreferences.getString(key,""));
        }
    }

    @Override
    public void onPause() {
        super.onPause();

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
