package com.ael_mnisiddique.com.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adaptive on 11/13/17.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter{

    private List<Item> mDataSrc;
    private Context mContext;

    public ExpandableAdapter(List<Item> mDataSrc, Context context) {
        this.mDataSrc = mDataSrc;
        this.mContext = context;
    }


    @Override
    public int getGroupCount() {
        return mDataSrc.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<String> subItems = mDataSrc.get(groupPosition).getmSubItems();
        return (subItems == null)? 0 : subItems.size();
    }

    @Override
    public Item getGroup(int groupPosition) {
        return mDataSrc.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        List<String> subItems = mDataSrc.get(groupPosition).getmSubItems();
        return (subItems == null)? "" : subItems.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item, null);
        }

        TextView gTitle = convertView.findViewById(R.id.group_title);
        gTitle.setText(getGroup(groupPosition).getmItemTitle());

        ImageView dropDown = convertView.findViewById(R.id.iv_dropdown);
        if(getChildrenCount(groupPosition) == 0){
            dropDown.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView chTitle = convertView.findViewById(R.id.child_title);
        chTitle.setText(getGroup(groupPosition).getmSubItems().get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
