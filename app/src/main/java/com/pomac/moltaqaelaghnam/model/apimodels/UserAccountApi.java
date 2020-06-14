package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.UserAccountResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAccountApi {

    @GET("moltka/public/api/myaccount")
    Call<UserAccountResponse> getUserAccount(@Query("token") String token);
}
