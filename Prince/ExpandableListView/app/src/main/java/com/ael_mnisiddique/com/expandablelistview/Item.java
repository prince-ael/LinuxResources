package com.ael_mnisiddique.com.expandablelistview;

import java.util.List;

/**
 * Created by adaptive on 11/15/17.
 */

public class Item {

    private String groupTitle;
    private List<String> childs;
    private int icon;

    public Item(String groupTitle, List<String> childs, int icon) {
        this.groupTitle = groupTitle;
        this.childs = childs;
        this.icon = icon;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public List<String> getChilds() {
        return childs;
    }

    public int getIcon() {
        return icon;
    }
}
