package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.DeleteFromWishListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeleteFromWishListApi {

    @FormUrlEncoded
    @POST("moltka/public/api/wishlist/delete")
    Call<DeleteFromWishListResponse> deleteFromWishList(@Field("token") String token, @Field("advertisement_id") int adId);
}
