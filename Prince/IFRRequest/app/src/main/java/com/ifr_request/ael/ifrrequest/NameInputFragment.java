package com.ifr_request.ael.ifrrequest;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import static com.ifr_request.ael.ifrrequest.InputFragment.KEY_VIEW_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class NameInputFragment extends DialogFragment {

    private EditText mNameInputEditText;
    private int mViewId;
    private TextInputLayout mNameWraper;

    public NameInputFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nifView = inflater.inflate(R.layout.fragment_name_input, container, false);

        mNameInputEditText = (EditText) nifView.findViewById(R.id.et_input_name);
        mNameWraper = (TextInputLayout) nifView.findViewById(R.id.til_name_wraper);

        Bundle args = getArguments();
        mViewId = args.getInt(KEY_VIEW_ID);

        mNameInputEditText.requestFocus();
        mNameInputEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mNameInputEditText.setImeActionLabel("Done",EditorInfo.IME_ACTION_DONE);

        openKeyBoard();

        mNameInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                boolean handled = false;

                if (actionId == EditorInfo.IME_NULL || actionId == EditorInfo.IME_ACTION_DONE) {

                    String name = mNameInputEditText.getText().toString();

                    if(name.length() == 0 || !name.matches("^$|[A-Za-z .]+")){
                        dismiss();
                        return handled;
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

        mNameInputEditText.setFilters(new InputFilter[]{getAlphabetFilter()});

        return nifView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void closeKeyBoard(){

        InputMethodManager imm = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mNameInputEditText.getWindowToken() , 0);

    }

    private void openKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED , InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private InputFilter getAlphabetFilter(){

        InputFilter filter = new InputFilter() {

            private boolean isLegal(String lastChar){
                return Pattern.compile("[A-Za-z .]+").matcher(lastChar).matches();
            }

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                int srcLen = source.length();
                int lastCharIndex = srcLen - 1;
                String lastChar = (lastCharIndex < 0)? "" : String.valueOf(source.charAt(lastCharIndex));

                if(!lastChar.isEmpty() && !isLegal(lastChar)){

                    String srcStr = source.toString();

                    return "//";
                }

                return null;
            }
        };

        return filter;
    }


}
