package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.AddToWishListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddToWishListApi {

    @FormUrlEncoded
    @POST("moltka/public/api/wishlist/add")
    Call<AddToWishListResponse> addToWishList(@Field("token") String token, @Field("advertisement_id") int adId);
}
