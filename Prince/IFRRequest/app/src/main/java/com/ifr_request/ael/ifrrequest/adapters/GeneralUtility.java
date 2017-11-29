package com.ifr_request.ael.ifrrequest.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.ifr_request.ael.ifrrequest.InputFragment;
import com.ifr_request.ael.ifrrequest.NameInputFragment;
import com.ifr_request.ael.ifrrequest.R;

/**
 * Created by adaptive on 7/31/17.
 */

public class GeneralUtility {

    public static void showInputNumberDialog(int viewId, Fragment invokingFragment, int containerViewId){

        InputFragment inputFragment = new InputFragment();
        Bundle args = new Bundle();
        args.putInt(InputFragment.KEY_VIEW_ID , viewId);
        inputFragment.setArguments(args);

        FragmentTransaction ft = invokingFragment.getFragmentManager().beginTransaction();
        ft.add(containerViewId , inputFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null).commit();
    }

    public static void showInputNameDialog(int viewId, Fragment invokingFragment, int containerViewId){

        NameInputFragment nameInputFragment = new NameInputFragment();
        Bundle args = new Bundle();
        args.putInt(InputFragment.KEY_VIEW_ID , viewId);
        nameInputFragment.setArguments(args);

        FragmentTransaction ft = invokingFragment.getFragmentManager().beginTransaction();
        ft.add(containerViewId , nameInputFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null).commit();
    }

    public static void putToPreference(String key, String value, Context context){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static void putToPreference(String key, int value, Context context){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static String getFormattedAmount(String amount){

        int n;

        try{
            n = Integer.parseInt(amount);
        }catch(NumberFormatException ex){

            return "";
        }

        int len = String.valueOf(n).length();
        StringBuilder sb = new StringBuilder(String.valueOf(n));

        if(len == 4 || len == 5){
            sb.insert(len-3,',');
        }

        if(len == 6 || len == 7){
            sb.insert(len-3,',');
            sb.insert(len-5,',');
        }

        if(len > 7){

            sb.insert(len-3,',');
            sb.insert(len-5,',');
            sb.insert(len-7,',');
        }

        if(sb.length() == 0){
            return "";
        }
        return sb.toString()+".00";
    }

    public static void ClearPreference(Context context){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
