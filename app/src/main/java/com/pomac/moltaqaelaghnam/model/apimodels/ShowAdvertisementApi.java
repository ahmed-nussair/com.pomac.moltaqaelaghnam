package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.ShowAdvertisementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowAdvertisementApi {

    @GET("moltka/public/api/advertisement/show")
    Call<ShowAdvertisementResponse> showAdvertisement(@Query("advertisement_id") int adId);
}
