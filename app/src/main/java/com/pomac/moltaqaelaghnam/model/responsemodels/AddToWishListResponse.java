package com.pomac.moltaqaelaghnam.model.responsemodels;

public class AddToWishListResponse {

    private int status;

    private String message;

    private String[] errors;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String[] getErrors() {
        return errors;
    }
}
