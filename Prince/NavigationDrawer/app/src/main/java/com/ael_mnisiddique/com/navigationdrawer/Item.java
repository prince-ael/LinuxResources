package com.ael_mnisiddique.com.navigationdrawer;

import java.util.List;

/**
 * Created by adaptive on 11/13/17.
 */

public class Item {

    private String mItemTitle;
    private int mItemIcon;
    private List<String> mSubItems;

    public String getmItemTitle() {
        return mItemTitle;
    }

    public void setmItemTitle(String mItemTitle) {
        this.mItemTitle = mItemTitle;
    }

    public int getmItemIcon() {
        return mItemIcon;
    }

    public void setmItemIcon(int mItemIcon) {
        this.mItemIcon = mItemIcon;
    }

    public List<String> getmSubItems() {
        return mSubItems;
    }

    public void setmSubItems(List<String> mSubItems) {
        this.mSubItems = mSubItems;
    }
}
