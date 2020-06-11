package com.pomac.moltaqaelaghnam.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Advertisement {

    private int id;

    private String title;

    private String phone;

    private String description;

    private String image;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("created_at")
    private String createdAt;

    private String imagePath;

    private String area;

    private String main;

    private User user;

    private List<Comment> comments;

    @SerializedName("in_wishlist")
    private int inWishList;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getArea() {
        return area;
    }

    public String getMain() {
        return main;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getInWishList() {
        return inWishList;
    }
}
