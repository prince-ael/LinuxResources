package com.ael_mnisiddique.com.expandablelistview;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandableList;
    private ExpandableAdapter mAdapter;
    private int groupCurrentlyExpanded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpandableList = findViewById(R.id.lv_exp);
        groupCurrentlyExpanded = -1; //No Group Is Expanded

        List<Item> dataSrc = new ArrayList<>();
        List<String> childs;

        dataSrc.add(new Item("Home",null, R.drawable.ic_home));

        childs = new ArrayList<>();
        childs.add("User Information");
        childs.add("User Settings");
        childs.add("Change Password");
        childs.add("Calendar");

        dataSrc.add(new Item("User",childs, R.drawable.ic_user));
        dataSrc.add(new Item("Patient Record",null, R.drawable.ic_patient_record));
        dataSrc.add(new Item("Demographic Editor",null, R.drawable.ic_editor));
        dataSrc.add(new Item("Alert Network",childs, R.drawable.ic_network_alert));

        mAdapter = new ExpandableAdapter(dataSrc,this);
        mExpandableList.setAdapter(mAdapter);

        mExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Toast.makeText(MainActivity.this, "Clicked",Toast.LENGTH_SHORT).show();
                ImageView t = v.findViewById(R.id.iv_group_indicator);
                t.setRotation(180);
                return false;
            }
        });

        mExpandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupCurrentlyExpanded != -1 && groupPosition != groupCurrentlyExpanded){
                    mExpandableList.collapseGroup(groupCurrentlyExpanded);
                }
                groupCurrentlyExpanded = groupPosition;
            }
        });

        mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }
}
