package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.WishListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WishListApi {

    @GET("moltka/public/api/wishlist")
    Call<WishListResponse> getWishList(@Query("token") String token);
}
