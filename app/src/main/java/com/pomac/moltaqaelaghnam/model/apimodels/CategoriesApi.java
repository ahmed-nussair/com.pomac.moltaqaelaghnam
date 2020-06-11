package com.pomac.moltaqaelaghnam.model.apimodels;

import com.pomac.moltaqaelaghnam.model.responsemodels.CategoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesApi {

    @GET("moltka/public/api/main")
    Call<CategoriesResponse> getCategories();
}
