package com.ifr_request.ael.ifrrequest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ifr_request.ael.ifrrequest.adapters.GeneralUtility;


/**
 * A simple {@link Fragment} subclass.
 */
public class CaptureFragment extends Fragment {

    private Spinner mPhotoIdTypeSpinner;

    public CaptureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View captureFragmentView = inflater.inflate(R.layout.fragment_capture, container, false);

        initView(captureFragmentView);

        return captureFragmentView;
    }

    private void initView(View captureFragmentView) {

        mPhotoIdTypeSpinner = (Spinner) captureFragmentView.findViewById(R.id.spinner_capture_photo_id_type);

        mPhotoIdTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                GeneralUtility.putToPreference(getString(R.string.pref_key_photo_id_pos) , position , getActivity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
