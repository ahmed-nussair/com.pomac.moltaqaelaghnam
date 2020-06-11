package com.pomac.moltaqaelaghnam.view.uimodels;

import android.view.View;

public class MoreItem {

    private String itemTitle;

    private int itemImageResource;

    private View.OnClickListener onClickListener;

    public MoreItem(String itemTitle, int itemImageResource, View.OnClickListener onClickListener) {
        this.itemTitle = itemTitle;
        this.itemImageResource = itemImageResource;
        this.onClickListener = onClickListener;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemImageResource() {
        return itemImageResource;
    }

    public void setItemImageResource(int itemImageResource) {
        this.itemImageResource = itemImageResource;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
