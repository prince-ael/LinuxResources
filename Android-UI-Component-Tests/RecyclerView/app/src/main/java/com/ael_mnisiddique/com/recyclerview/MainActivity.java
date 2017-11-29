package com.ael_mnisiddique.com.recyclerview;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRVItemClickListener{

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private Toolbar mToolbar;
    private ConstraintSet mInitialConstraints;
    private ConstraintSet mModifiedConstraints;
    private ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_test);

        mToolbar = findViewById(R.id.toolbar_3);
        setSupportActionBar(mToolbar);

        initConstraintSet();

        SkeletonConfig skeletonConfig = new SkeletonConfig().build();

        mAdapter = new CustomAdapter(getFakeData(),this, skeletonConfig);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        mRecyclerView.setAdapter(mAdapter);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                mAdapter.onLoadingFinished();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(r,700);
    }

    private List<String> getFakeData(){

        List<String> fakeData = new ArrayList<>();

        for(int i = 1; i <= 100; i++){
            fakeData.add("Fake Enough "+i);
        }

        return fakeData;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"onItemClick gets the call",Toast.LENGTH_SHORT).show();

        swapViews();
    }

    private void initConstraintSet() {

        root = mToolbar.findViewById(R.id.root_toolbar3);

        mInitialConstraints = new ConstraintSet();
        mInitialConstraints.clone(root);

        mModifiedConstraints = new ConstraintSet();
        mModifiedConstraints.clone(root);
    }

    private void swapViews(){
        TransitionManager.beginDelayedTransition(root);
        mModifiedConstraints.clear(R.id.btn_menu_alarm_3);

        mModifiedConstraints.connect(R.id.btn_menu_alarm_3,
                ConstraintSet.RIGHT,
                R.id.parent,
                ConstraintSet.RIGHT);
        mModifiedConstraints.connect(R.id.btn_menu_alarm_3,
                ConstraintSet.TOP,
                R.id.parent,
                ConstraintSet.TOP);
        mModifiedConstraints.connect(R.id.btn_menu_alarm_3,
                ConstraintSet.BOTTOM,
                R.id.parent,
                ConstraintSet.BOTTOM);
        mModifiedConstraints.constrainWidth(R.id.btn_menu_alarm_3,getPixelFrom(56));
        mModifiedConstraints.constrainHeight(R.id.btn_menu_alarm_3,ConstraintSet.MATCH_CONSTRAINT);

        mModifiedConstraints.clear(R.id.separator_alarm_logout);
        mModifiedConstraints.connect(R.id.separator_alarm_logout,
                ConstraintSet.RIGHT,
                R.id.btn_menu_alarm_3,
                ConstraintSet.LEFT);
        mModifiedConstraints.connect(R.id.separator_alarm_logout,
                ConstraintSet.TOP,
                R.id.parent,
                ConstraintSet.TOP);
        mModifiedConstraints.connect(R.id.separator_alarm_logout,
                ConstraintSet.BOTTOM,
                R.id.parent,
                ConstraintSet.BOTTOM);
        mModifiedConstraints.constrainWidth(R.id.separator_alarm_logout,getPixelFrom(1));
        mModifiedConstraints.constrainHeight(R.id.separator_alarm_logout,ConstraintSet.MATCH_CONSTRAINT);

        mModifiedConstraints.clear(R.id.btn_menu_logout_3);
        mModifiedConstraints.connect(R.id.btn_menu_logout_3,
                ConstraintSet.RIGHT,
                R.id.separator_alarm_logout,
                ConstraintSet.LEFT);
        mModifiedConstraints.connect(R.id.btn_menu_logout_3,
                ConstraintSet.TOP,
                R.id.parent,
                ConstraintSet.TOP);
        mModifiedConstraints.connect(R.id.btn_menu_logout_3,
                ConstraintSet.BOTTOM,
                R.id.parent,
                ConstraintSet.BOTTOM);
        mModifiedConstraints.constrainWidth(R.id.btn_menu_logout_3,getPixelFrom(56));
        mModifiedConstraints.constrainHeight(R.id.btn_menu_logout_3,ConstraintSet.MATCH_CONSTRAINT);


        mModifiedConstraints.applyTo(root);
    }

    private void getBackToInitialPosition(){
        TransitionManager.beginDelayedTransition(root);
        mInitialConstraints.applyTo(root);
    }

    private int getPixelFrom(int dp){
        DisplayMetrics display = getResources().getDisplayMetrics();
        int density = Math.round((display.density * 160));

        return Math.round((density/160) * dp);
    }
}
