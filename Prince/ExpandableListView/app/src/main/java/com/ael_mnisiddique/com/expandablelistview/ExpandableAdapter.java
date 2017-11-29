package com.ael_mnisiddique.com.expandablelistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adaptive on 11/15/17.
 */

class ExpandableAdapter extends BaseExpandableListAdapter{

    private List<Item> mItems;
    private Context mContext;
    private Map<Integer,View> groupIndicators;

    public ExpandableAdapter(List<Item> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
        groupIndicators = new HashMap<>();
    }

    @Override
    public int getGroupCount() {
        return mItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<String> childs = mItems.get(groupPosition).getChilds();
        return (childs == null)? 0 : childs.size();
    }

    @Override
    public Item getGroup(int groupPosition) {
        return mItems.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mItems.get(groupPosition).getChilds().get(childPosition);
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

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.group_item,parent,false);
        }

        Item item = getGroup(groupPosition);

        TextView groupTitle = convertView.findViewById(R.id.tv_group_title);
        groupTitle.setText(item.getGroupTitle());

        ImageView groupIcon = convertView.findViewById(R.id.iv_group_icon);
        groupIcon.setImageResource(item.getIcon());

        ImageView groupIndicator = convertView.findViewById(R.id.iv_group_indicator);

        if(getChildrenCount(groupPosition) == 0){
            groupIndicator.setVisibility(View.INVISIBLE);
        }else{
            groupIndicator.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.child_item,parent,false);
        }

        TextView childTitle = convertView.findViewById(R.id.tv_child_title);
        childTitle.setText(getChild(groupPosition,childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void animateGroupIndicator(ImageView imgView, int fromDegrees, int toDegrees){

        RotateAnimation rotateAnim = new RotateAnimation(fromDegrees, toDegrees,
                imgView.getWidth()/2, imgView.getHeight()/2);

        rotateAnim.setDuration(150);
        rotateAnim.setFillAfter(true);
        imgView.startAnimation(rotateAnim);

        if(fromDegrees == 0 && toDegrees == 180){
            Animation anim = AnimationUtils.loadAnimation(mContext,R.anim.rotate_up);
            imgView.startAnimation(anim);
        }else{
            Animation anim = AnimationUtils.loadAnimation(mContext,R.anim.rotate_down);
            imgView.startAnimation(anim);
        }
    }
}
