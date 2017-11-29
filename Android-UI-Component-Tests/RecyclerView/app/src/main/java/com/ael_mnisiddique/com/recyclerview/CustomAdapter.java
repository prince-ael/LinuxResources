package com.ael_mnisiddique.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.rmiri.skeleton.SkeletonGroup;

/**
 * Created by adaptive on 11/23/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> fakeData;
    private Context mContext;
    private SkeletonConfig mSkConfig;
    private OnRVItemClickListener itemClickListener;

    public CustomAdapter(List<String> fakeData, Context mContext, SkeletonConfig skeletonConfig) {
        this.fakeData = fakeData;
        this.mContext = mContext;
        mSkConfig = skeletonConfig;
        itemClickListener = (OnRVItemClickListener) mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.rv_row,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder mHolder = (MyViewHolder) holder;

        if (mSkConfig.isSkeletonIsOn()) {
            //need show s for 2 cards
            mHolder.skeletonGroup.setAutoPlay(true);
            return;
        } else {
            mHolder.skeletonGroup.setShowSkeleton(false);
            mHolder.skeletonGroup.finishAnimation();
        }

        mHolder.tv.setText(fakeData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mSkConfig.isSkeletonIsOn()) {
            // show just 2 card item in recyclerView
            return 100;
        } else {
            //normal show card item in recyclerView
            return fakeData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv;
        private SkeletonGroup skeletonGroup;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            skeletonGroup = itemView.findViewById(R.id.skeletonGroup);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void onLoadingFinished(){
        mSkConfig.setSkeletonIsOn(false);
        notifyDataSetChanged();
    }
}
