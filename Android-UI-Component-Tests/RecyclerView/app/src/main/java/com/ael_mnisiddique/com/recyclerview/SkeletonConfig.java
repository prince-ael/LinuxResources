package com.ael_mnisiddique.com.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by adaptive on 11/23/17.
 */

public class SkeletonConfig {

    private boolean skeletonIsOn = true;
    private int recyclerViewHeight = 0, itemHeight = 0, numberItemShow = 1;
    private RecyclerView recyclerView;

    public SkeletonConfig() {
    }

    public SkeletonConfig(boolean skeletonIsOn, int recyclerViewHeight, int itemHeight, int numberItemShow, RecyclerView recyclerView) {
        this.skeletonIsOn = skeletonIsOn;
        this.recyclerViewHeight = recyclerViewHeight;
        this.itemHeight = itemHeight;
        this.numberItemShow = numberItemShow;
        this.recyclerView = recyclerView;
    }

    public boolean isSkeletonIsOn() {
        return skeletonIsOn;
    }

    public SkeletonConfig setSkeletonIsOn(boolean skeletonIsOn) {
        this.skeletonIsOn = skeletonIsOn;
        return this;
    }

    public int getRecyclerViewHeight() {
        return recyclerViewHeight;
    }

    public SkeletonConfig setRecyclerViewHeight(int recyclerViewHeight) {
        this.recyclerViewHeight = recyclerViewHeight;
        return this;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public SkeletonConfig setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public int getNumberItemShow() {
        return numberItemShow;
    }

    public SkeletonConfig setNumberItemShow(int numberItemShow) {
        this.numberItemShow = numberItemShow;
        return this;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public SkeletonConfig setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public SkeletonConfig build() {
        return new SkeletonConfig(skeletonIsOn, recyclerViewHeight, itemHeight, numberItemShow, recyclerView);
    }
}
