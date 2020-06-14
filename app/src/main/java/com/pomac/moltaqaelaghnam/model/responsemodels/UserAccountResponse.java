package com.pomac.moltaqaelaghnam.model.responsemodels;

import com.google.gson.annotations.SerializedName;
import com.pomac.moltaqaelaghnam.model.User;

public class UserAccountResponse {

    private int status;

    @SerializedName("user_data")
    private User userData;

    public int getStatus() {
        return status;
    }

    public User getUserData() {
        return userData;
    }
}
