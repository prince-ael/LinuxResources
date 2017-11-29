package com.ael_mnisiddique.com.navigationdrawer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListviewFragment extends Fragment {

    private ExpandableListView mExpandableListView;
    private ExpandableAdapter mExpandableAdapter;

    public ListviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExpandableListView = view.findViewById(R.id.lv_expandable);

        List<Item> dataSrc = new ArrayList<>();


        Item item = new Item();
        item.setmItemTitle("Home");
        item.setmItemIcon(R.drawable.ic_home_black_24px);
        item.setmSubItems(null);
        dataSrc.add(item);

        item = new Item();
        item.setmItemTitle("User");
        item.setmItemIcon(R.drawable.ic_account_circle_black_24px);
        List<String> subItem = new ArrayList<>();
        subItem.add("User Info");
        subItem.add("User Setting");
        subItem.add("Change Password");
        subItem.add("Calendar");
        item.setmSubItems(subItem);
        dataSrc.add(item);

        item = new Item();
        item.setmItemTitle("Patient Record");
        item.setmSubItems(null);
        dataSrc.add(item);

        item = new Item();
        item.setmItemTitle("Demographic Editpr");
        item.setmItemIcon(R.drawable.ic_mode_edit_black_24px);
        item.setmSubItems(null);
        dataSrc.add(item);

        item = new Item();
        item.setmItemTitle("Alert Network");
        item.setmSubItems(subItem);
        dataSrc.add(item);

        mExpandableAdapter = new ExpandableAdapter(dataSrc,getActivity());
        mExpandableListView.setAdapter(mExpandableAdapter);
    }
}
