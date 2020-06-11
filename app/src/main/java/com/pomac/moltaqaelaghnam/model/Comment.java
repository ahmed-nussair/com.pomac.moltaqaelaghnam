package com.pomac.moltaqaelaghnam.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int id;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("advertisement_id")
    private String advertisementId;

    private String comment;

    private User user;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }
}
