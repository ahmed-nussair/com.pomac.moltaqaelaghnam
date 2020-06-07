package com.pomac.moltaqaelaghnam.view.uimodels;

import android.view.View;

public class DrawerItem {

    private String drawerItemTitle;

    private int drawerItemImageResource;

    private View.OnClickListener onClickListener;

    public DrawerItem(String drawerItemTitle, int drawerItemImageResource, View.OnClickListener onClickListener) {
        this.drawerItemTitle = drawerItemTitle;
        this.drawerItemImageResource = drawerItemImageResource;
        this.onClickListener = onClickListener;
    }

    public String getDrawerItemTitle() {
        return drawerItemTitle;
    }

    public int getDrawerItemImageResource() {
        return drawerItemImageResource;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
