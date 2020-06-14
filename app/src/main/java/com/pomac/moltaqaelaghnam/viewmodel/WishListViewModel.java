package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.WishListApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.WishListResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class WishListViewModel extends ViewModel {

    private MutableLiveData<WishListResponse> mutableLiveData;

    public LiveData<WishListResponse> getWishList(String token) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadWishList(token);
        }
        return mutableLiveData;
    }

    private void loadWishList(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WishListApi api = retrofit.create(WishListApi.class);

        Call<WishListResponse> call = api.getWishList(token);

        call.enqueue(new Callback<WishListResponse>() {

            @EverythingIsNonNull
            @Override
            public void onResponse(Call<WishListResponse> call, Response<WishListResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<WishListResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
