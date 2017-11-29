package com.ifr_request.ael.ifrrequest;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ifr_request.ael.ifrrequest.adapters.GeneralUtility;
import com.ifr_request.ael.ifrrequest.adapters.IFRPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private IFRPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
        mPagerAdapter = new IFRPagerAdapter(getSupportFragmentManager() , this);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        mTabLayout.setupWithViewPager(mViewPager);

        TabLayout.Tab firstTab = mTabLayout.getTabAt(0);

        if(firstTab.isSelected()){

            turnToBold(firstTab);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                turnToBold(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                CharSequence tabTitle = mPagerAdapter.getPageTitle(tab.getPosition());
                tab.setText(tabTitle);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void turnToBold(TabLayout.Tab tab){

        CharSequence tabTextCharSequence = tab.getText();
        int end = tabTextCharSequence.length();
        SpannableString tabTextSpannable = new SpannableString(tabTextCharSequence);

        tabTextSpannable.setSpan(new StyleSpan(Typeface.BOLD) , 0 , end , 0);
        tabTextSpannable.setSpan(new ForegroundColorSpan(Color.WHITE), 0 , end , 0);
        tab.setText(tabTextSpannable);
        double density = getResources().getDisplayMetrics().density;
        Log.d("Screen Density",String.valueOf(density));
    }

    @Override
    protected void onStop() {
        super.onStop();
        GeneralUtility.ClearPreference(this);
        Log.d("MainActivity","onStop Called");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
        Log.d("MainActivity","onBack Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        GeneralUtility.ClearPreference(this);
    }
}
