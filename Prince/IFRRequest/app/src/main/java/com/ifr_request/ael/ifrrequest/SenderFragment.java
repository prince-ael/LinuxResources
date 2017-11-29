package com.ifr_request.ael.ifrrequest;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ifr_request.ael.ifrrequest.adapters.GeneralUtility;


/**
 * A simple {@link Fragment} subclass.
 */
public class SenderFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView mExchangeHouseLabelTextView;
    private TextView mPinLabelTextView;
    private TextView mTTLabelTextView;
    private TextView mSenderNameLabelTextView;
    private TextView mSenderCountryLabelTextView;

    private Spinner mExchangeHouseSpinner;
    private Spinner mSenderCountrySpinner;

    private EditText mPinEditText;
    private EditText mTTEditText;
    private EditText mSenderNameEditText;
    private RelativeLayout reviewButton;

    public SenderFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View senderFragmentView = inflater.inflate(R.layout.fragment_sender, container, false);

        initViews(senderFragmentView);
        initAction();

        return senderFragmentView;
    }



    private void initViews(View senderFragmentView) {

        mExchangeHouseLabelTextView = (TextView) senderFragmentView.findViewById(R.id.tv_sender_exchange_house_label);
        mPinLabelTextView = (TextView) senderFragmentView.findViewById(R.id.tv_sender_pin_label);
        mTTLabelTextView = (TextView) senderFragmentView.findViewById(R.id.tv_sender_tt_label);
        mSenderCountryLabelTextView = (TextView) senderFragmentView.findViewById(R.id.tv_sender_country_label);
        mSenderNameLabelTextView = (TextView) senderFragmentView.findViewById(R.id.tv_sender_name_label);

        mSenderCountrySpinner = (Spinner) senderFragmentView.findViewById(R.id.spinner_sender_country);
        mExchangeHouseSpinner = (Spinner) senderFragmentView.findViewById(R.id.spinner_sender_exchange_house);

        mPinEditText = (EditText) senderFragmentView.findViewById(R.id.et_sender_pin);
        mPinEditText.setKeyListener(null);

        mTTEditText = (EditText) senderFragmentView.findViewById(R.id.et_sender_tt);
        mTTEditText.setKeyListener(null);

        mSenderNameEditText = (EditText) senderFragmentView.findViewById(R.id.et_sender_name);
        mSenderNameEditText.setKeyListener(null);

        mSenderCountryLabelTextView.setVisibility(View.VISIBLE);
        mExchangeHouseLabelTextView.setVisibility(View.VISIBLE);

        reviewButton = (RelativeLayout) senderFragmentView.findViewById(R.id.rl_as_review_btn);
    }

    private void initAction() {

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.add(android.R.id.content , new ReviewFragment()).addToBackStack(null).commit();
            }
        });

        mPinEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtility.showInputNumberDialog(R.id.et_sender_pin,
                        SenderFragment.this,
                        android.R.id.content);
            }
        });

        mTTEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtility.showInputNumberDialog(R.id.et_sender_tt,
                        SenderFragment.this,
                        android.R.id.content);
            }
        });

        mSenderNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtility.showInputNameDialog(R.id.et_sender_name , SenderFragment.this , android.R.id.content);
            }
        });

        mExchangeHouseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                GeneralUtility.putToPreference(getString(R.string.pref_key_exchange_house),
                        getResources().getStringArray(R.array.array_exchange_houses)[position],getActivity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSenderCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                GeneralUtility.putToPreference(getString(R.string.pref_key_sender_country),
                        getResources().getStringArray(R.array.array_sender_countries)[position],getActivity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value;
        if(key.equals(getString(R.string.pref_key_pin))){
            value = sharedPreferences.getString(key, "");
            mPinEditText.setText(value);
            mPinLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }else if(key.equals(getString(R.string.pref_key_tt))){
            value = sharedPreferences.getString(key, "");
            mTTEditText.setText(value);
            mTTLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }else if(key.equals(getString(R.string.pref_key_sender_name))){
            value = sharedPreferences.getString(key, "");
            mSenderNameEditText.setText(value);
            mSenderNameLabelTextView.setVisibility((value.equals(""))?View.INVISIBLE : View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("SenderFragment","in On Resumed State");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedPref.registerOnSharedPreferenceChangeListener(this);
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

        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);

    }

}
