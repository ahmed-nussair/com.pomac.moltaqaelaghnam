package com.pomac.moltaqaelaghnam.model.responsemodels;

import com.pomac.moltaqaelaghnam.model.WishList;

import java.util.List;

public class WishListResponse {

    private int status;

    private List<WishList> data;

    public int getStatus() {
        return status;
    }

    public List<WishList> getData() {
        return data;
    }
}
