package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.DeleteFromWishListApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.DeleteFromWishListResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class DeleteFromWishListViewModel extends ViewModel {

    private MutableLiveData<DeleteFromWishListResponse> mutableLiveData;

    public LiveData<DeleteFromWishListResponse> deleteFromWishList(String token, int adId) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            doDeleteFromWishList(token, adId);
        }
        return mutableLiveData;
    }

    private void doDeleteFromWishList(String token, int adId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DeleteFromWishListApi api = retrofit.create(DeleteFromWishListApi.class);

        Call<DeleteFromWishListResponse> call = api.deleteFromWishList(token, adId);

        call.enqueue(new Callback<DeleteFromWishListResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<DeleteFromWishListResponse> call, Response<DeleteFromWishListResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<DeleteFromWishListResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
