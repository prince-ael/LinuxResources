package com.ifr_request.ael.ifrrequest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ifr_request.ael.ifrrequest.CaptureFragment;
import com.ifr_request.ael.ifrrequest.R;
import com.ifr_request.ael.ifrrequest.RecieverFragment;
import com.ifr_request.ael.ifrrequest.SenderFragment;

import java.util.ArrayList;

/**
 * Created by adaptive on 7/27/17.
 */

public class IFRPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> pageSrc;
    private String [] pageTitles;

    public IFRPagerAdapter(FragmentManager fm , Context context) {
        super(fm);

        pageSrc = new ArrayList<>();
        pageSrc.add(new CaptureFragment());
        pageSrc.add(new RecieverFragment());
        pageSrc.add(new SenderFragment());

        String[] pageTitles = {context.getResources().getString(R.string.tab_title1),
                context.getResources().getString(R.string.tab_title2),
                context.getResources().getString(R.string.tab_title3)};
        this.pageTitles = pageTitles;

    }

    @Override
    public Fragment getItem(int position) {
        return (position >= 0 && position <3)? pageSrc.get(position) : null ;
    }

    @Override
    public int getCount() {
        return pageSrc.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
