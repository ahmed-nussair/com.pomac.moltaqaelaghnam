package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.UpdateProfileResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UpdateProfileApi {

    @FormUrlEncoded
    @POST("moltka/public/api/account/update-profile")
    Call<UpdateProfileResponse> updateProfile(@Field("token") String token, @Field("name") String name);
}
