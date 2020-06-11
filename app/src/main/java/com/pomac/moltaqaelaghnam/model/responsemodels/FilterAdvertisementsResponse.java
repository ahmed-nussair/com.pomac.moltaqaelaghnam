package com.pomac.moltaqaelaghnam.model.responsemodels;

import com.google.gson.annotations.SerializedName;
import com.pomac.moltaqaelaghnam.model.Advertisement;

import java.util.List;

public class FilterAdvertisementsResponse {

    private int status;

    private List<Advertisement> data;

    @SerializedName("last_page")
    private int lastPage;

    public int getStatus() {
        return status;
    }

    public List<Advertisement> getData() {
        return data;
    }

    public int getLastPage() {
        return lastPage;
    }
}
