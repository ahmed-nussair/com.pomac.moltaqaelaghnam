package com.pomac.moltaqaelaghnam.view.uimodels;

public class Comment {

    private String userName;

    private String comment;

    private String imagePath;

    public Comment(String userName, String comment, String imagePath) {
        this.userName = userName;
        this.comment = comment;
        this.imagePath = imagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
