package com.example.android.sunshine.utilities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.android.sunshine.R;

/**
 * Created by adaptive on 6/22/17.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.preference_screen);
        handlingPreferenceSummary();
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference firedPrefOnScreen = findPreference(key);

        if(!(firedPrefOnScreen instanceof CheckBoxPreference)){

            setPreferenceSummary(firedPrefOnScreen , sharedPreferences.getString(key , ""));
        }
    }

    private void setPreferenceSummary(Preference preference , String value){

        if(preference instanceof EditTextPreference){

            EditTextPreference locationPreference = (EditTextPreference) preference;
            locationPreference.setSummary(value);

        }else if(preference instanceof ListPreference ){

            ListPreference unitsPref = (ListPreference) preference;

            int selectedUnitIndex    = unitsPref.findIndexOfValue(value);
            String selectedUnitLabel =  (unitsPref.getEntries()[selectedUnitIndex]).toString();
            unitsPref.setSummary(selectedUnitLabel);
        }
    }

    private void handlingPreferenceSummary(){

        PreferenceScreen prefScreen = getPreferenceScreen();
        SharedPreferences sharedPref = prefScreen.getSharedPreferences();
        int numOfPrefences = prefScreen.getPreferenceCount();

        //While Iteration Direction Is Not Important
        while(--numOfPrefences > -1){

            Preference prefOnScreen = prefScreen.getPreference(numOfPrefences);

            if(!(prefOnScreen instanceof CheckBoxPreference)){

                setPreferenceSummary(prefOnScreen , sharedPref.getString(prefOnScreen.getKey() , ""));
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
