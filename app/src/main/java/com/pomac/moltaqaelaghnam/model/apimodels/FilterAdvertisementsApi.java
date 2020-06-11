package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.FilterAdvertisementsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilterAdvertisementsApi {

    @GET("moltka/public/api/advertisement")
    Call<FilterAdvertisementsResponse> filterAdvertisements(@Query("area_id") int areaId, @Query("main_id") int mainId);
}
