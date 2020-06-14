package com.pomac.moltaqaelaghnam.model;

import com.google.gson.annotations.SerializedName;

public class WishList {

    private int id;

    @SerializedName("advertisement_id")
    private String advertisementId;

    @SerializedName("user_id")
    private String userId;

    private Advertisement advertisement;

    public int getId() {
        return id;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public String getUserId() {
        return userId;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }
}
