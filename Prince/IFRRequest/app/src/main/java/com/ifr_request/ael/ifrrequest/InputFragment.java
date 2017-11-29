package com.ifr_request.ael.ifrrequest;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifr_request.ael.ifrrequest.adapters.GeneralUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends DialogFragment implements View.OnClickListener{

    private TextInputLayout mNumberWrapper;
    private EditText mInputEditText;

    private CardView mZeroBtn;
    private CardView mOneBtn;
    private CardView mTwoBtn;
    private CardView mThreeBtn;
    private CardView mFourBtn;
    private CardView mFiveBtn;
    private CardView mSixBtn;
    private CardView mSevenBtn;
    private CardView mEightBtn;
    private CardView mNineBtn;

    private CardView mEraseBtn;
    private CardView mDoneBtn;
    private RelativeLayout mCustomKeyPad;

    private String mErrorMessage;
    private String mFloatingLabel;
    private String mPreExistingData;
    public static final String KEY_VIEW_ID="view_id";
    private int mViewId;

    private SharedPreferences mIFRSharedPreference;
    private SharedPreferences.Editor mEditor;
    private KeyListener mDefaultKeyListener;

    private static final int MAX_LENGTH_NID = 17;
    private static final int MAX_LENGTH_MOBILE = 11;
    private static final int MAX_LENGTH_PIN = 19;
    private static final int MAX_LENGTH_TT = 8;


    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inputFragmentView =  inflater.inflate(R.layout.fragment_input, container, false);

        Bundle args = getArguments();
        mViewId = args.getInt(KEY_VIEW_ID);

        mIFRSharedPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mIFRSharedPreference.edit();
        labelAndMessageFor(mViewId);

        initViews(inputFragmentView);

        return inputFragmentView ;
    }

    private void labelAndMessageFor(int mViewId) {

        switch(mViewId){

            case R.id.tv_review_nid_no_data:
            case R.id.et_reciever_photoid_no :
                mErrorMessage  = getString(R.string.err_msg_for_photo_id);
                mFloatingLabel = getString(R.string.label_photo_id_Number);
                mPreExistingData = mIFRSharedPreference.getString(getString(R.string.pref_key_photo_id),"");
                break;
            case R.id.tv_review_mobile_no_data:
            case R.id.et_reciever_mobile_no :
                mErrorMessage  = getString(R.string.err_msg_for_mobile_no);
                mFloatingLabel = getString(R.string.label_mobile_number);
                mPreExistingData = mIFRSharedPreference.getString(getString(R.string.pref_key_mobile),"");
                break;
            case R.id.tv_review_pin_data:
            case R.id.et_sender_pin :
                mErrorMessage  = getString(R.string.err_msg_for_pin);
                mFloatingLabel = getString(R.string.label_pin);
                mPreExistingData = mIFRSharedPreference.getString(getString(R.string.pref_key_pin),"");
                break;
            case R.id.tv_review_ttno_data:
            case R.id.et_sender_tt :
                mErrorMessage  = getString(R.string.err_msg_for_tt_no);
                mFloatingLabel = getString(R.string.label_ttno);
                mPreExistingData = mIFRSharedPreference.getString(getString(R.string.pref_key_tt),"");
                break;
            case R.id.tv_review_taka_amount_data:
            case R.id.et_reciever_taka_amount :
                mErrorMessage  = getString(R.string.err_msg_for_taka_amount);
                mFloatingLabel = getString(R.string.label_amount_in_taka);
                mPreExistingData = mIFRSharedPreference.getString(getString(R.string.pref_key_taka_amount),"");
                break;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void initViews(View inputFragmentView) {

        mNumberWrapper = (TextInputLayout) inputFragmentView.findViewById(R.id.til_number_wraper);
        mInputEditText = (EditText) inputFragmentView.findViewById(R.id.et_input_number);
        mInputEditText.requestFocus();
        mInputEditText.setKeyListener(null);

        mZeroBtn = (CardView) inputFragmentView.findViewById(R.id.btn_zero);
        mZeroBtn.setOnClickListener(this);

        mOneBtn = (CardView) inputFragmentView.findViewById(R.id.btn_one);
        mOneBtn.setOnClickListener(this);

        mTwoBtn = (CardView) inputFragmentView.findViewById(R.id.btn_two);
        mTwoBtn.setOnClickListener(this);

        mThreeBtn = (CardView) inputFragmentView.findViewById(R.id.btn_three);
        mThreeBtn.setOnClickListener(this);

        mFourBtn = (CardView) inputFragmentView.findViewById(R.id.btn_four);
        mFourBtn.setOnClickListener(this);

        mFiveBtn = (CardView) inputFragmentView.findViewById(R.id.btn_five);
        mFiveBtn.setOnClickListener(this);

        mSixBtn = (CardView) inputFragmentView.findViewById(R.id.btn_six);
        mSixBtn.setOnClickListener(this);

        mSevenBtn = (CardView) inputFragmentView.findViewById(R.id.btn_seven);
        mSevenBtn.setOnClickListener(this);

        mEightBtn = (CardView) inputFragmentView.findViewById(R.id.btn_eight);
        mEightBtn.setOnClickListener(this);

        mNineBtn = (CardView) inputFragmentView.findViewById(R.id.btn_nine);
        mNineBtn.setOnClickListener(this);

        mEraseBtn = (CardView) inputFragmentView.findViewById(R.id.btn_backspace);
        mEraseBtn.setOnClickListener(this);

        mDoneBtn = (CardView) inputFragmentView.findViewById(R.id.btn_done);
        mDoneBtn.setOnClickListener(this);

        mCustomKeyPad = (RelativeLayout) inputFragmentView.findViewById(R.id.rl_as_custom_keypad);

        mNumberWrapper.setHint(mFloatingLabel);

        if(!mPreExistingData.isEmpty()){
            mInputEditText.setText(mPreExistingData);
            mInputEditText.setSelection(mPreExistingData.length());
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_zero  : buttonZeroAction();break;
            case R.id.btn_one   : mInputEditText.append("1");break;
            case R.id.btn_two   : mInputEditText.append("2");break;
            case R.id.btn_three : mInputEditText.append("3");break;
            case R.id.btn_four  : mInputEditText.append("4");break;
            case R.id.btn_five  : mInputEditText.append("5");break;
            case R.id.btn_six   : mInputEditText.append("6");break;
            case R.id.btn_seven : mInputEditText.append("7");break;
            case R.id.btn_eight : mInputEditText.append("8");break;
            case R.id.btn_nine  : mInputEditText.append("9");break;
            case R.id.btn_done  : actionDone();break;
            case R.id.btn_backspace : deleteLastDigit(); break;

        }
    }

    private void deleteLastDigit(){
        int len = mInputEditText.getText().length();
        if(len > 0){
            mInputEditText.getText().delete(len -1 , len);
        }

        if(mInputEditText.getText().length() == 0){
            mNumberWrapper.setError(null);
        }
    }

    private void actionDone(){
        String data = mInputEditText.getText().toString();
        if( data.length() == 0){
            return;
        }

        switch(mViewId){

            case R.id.tv_review_nid_no_data:
            case R.id.et_reciever_photoid_no :
                if(isPhotoIdValid(data)){
                    mEditor.putString(getString(R.string.pref_key_photo_id), data);
                    mEditor.apply();
                    break;
                }
                mNumberWrapper.setError(mErrorMessage);
                return;
            case R.id.tv_review_mobile_no_data:
            case R.id.et_reciever_mobile_no :
                if(isMobileNoValid(data)){
                    mEditor.putString(getString(R.string.pref_key_mobile), data);
                    mEditor.apply();
                    break;
                }
                mNumberWrapper.setError(mErrorMessage);
                return;
            case R.id.tv_review_pin_data:
            case R.id.et_sender_pin :
                if(isPinValid(data)){
                    mEditor.putString(getString(R.string.pref_key_pin), data);
                    mEditor.apply();
                    break;
                }
                mNumberWrapper.setError(mErrorMessage);
                return;
            case R.id.tv_review_ttno_data:
            case R.id.et_sender_tt :
                if(isTtValid(data)){

                    mEditor.putString(getString(R.string.pref_key_tt), data);
                    mEditor.apply();
                    break;
                }
                mNumberWrapper.setError(mErrorMessage);
                return;
            case R.id.tv_review_taka_amount_data:
            case R.id.et_reciever_taka_amount :
                if(isTakaAmountOk(data)){

                    mEditor.putString(getString(R.string.pref_key_taka_amount), data);
                    mEditor.apply();
                    break;
                }
                mNumberWrapper.setError(mErrorMessage);
                return;
        }

        dismiss();
    }

    private boolean isPhotoIdValid(String photoId){

        return photoId.length() == 13 || photoId.length() == 17;
    }

    private boolean isPinValid(String pinNo){

        return true;
    }

    private boolean isTtValid(String ttNo){

        return true;
    }

    private boolean isTakaAmountOk(String takaAmount){


        return true;
    }

    private boolean isMobileNoValid(String mobileNo){

        mobileNo = mobileNo.trim();

        boolean mobileValidity = mobileNo.length() == 11;
        String regEx = "01[156789][0-9]+";

        Pattern validMobileNoPat = Pattern.compile(regEx);
        Matcher givenPattern = validMobileNoPat.matcher(mobileNo);
        mobileValidity &= givenPattern.matches();


        return mobileValidity;
    }

    public void turnToNormalEditText(){

        mCustomKeyPad.setVisibility(View.INVISIBLE);

        mInputEditText.setKeyListener(mDefaultKeyListener);
        mInputEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mInputEditText.setImeActionLabel("Done",EditorInfo.IME_ACTION_DONE);

        openKeyBoard();

        mInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                boolean handled = false;

                if (actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_DONE) {

                    String name = mInputEditText.getText().toString();

                    if(name.length() == 0){
                        dismiss();
                    }
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor edit = pref.edit();
                    if(mViewId == R.id.et_reciever_name || mViewId == R.id.tv_review_name_data){
                        edit.putString(getString(R.string.pref_key_reciever_name),name);
                    }else if(mViewId == R.id.et_sender_name || mViewId == R.id.tv_review_sender_name_data){
                        edit.putString(getString(R.string.pref_key_sender_name),name);
                    }
                    handled = true;
                    edit.apply();
                    closeKeyBoard();
                    dismiss();

                }

                return handled;
            }
        });
    }

    private void closeKeyBoard(){

        InputMethodManager imm = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInputEditText.getWindowToken() , 0);

    }

    private void openKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED , InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void setMaxLength(int maxLen){

        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLen);
        mInputEditText.setFilters(fArray);
    }

    private void buttonZeroAction(){

        if(mFloatingLabel.equals(getString(R.string.label_amount_in_taka))){

            String data = mInputEditText.getText().toString();
            if(data.equals("0") && data.length() == 1){
                return;
            }
        }

        mInputEditText.append("0");
    }
}
