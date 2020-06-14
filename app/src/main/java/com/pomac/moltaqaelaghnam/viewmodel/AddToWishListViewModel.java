package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.AddToWishListApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.AddToWishListResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class AddToWishListViewModel extends ViewModel {

    private MutableLiveData<AddToWishListResponse> mutableLiveData;

    public LiveData<AddToWishListResponse> addToWishList(String token, int adId) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            doAddToWishList(token, adId);
        }
        return mutableLiveData;
    }

    private void doAddToWishList(String token, int adId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AddToWishListApi api = retrofit.create(AddToWishListApi.class);

        Call<AddToWishListResponse> call = api.addToWishList(token, adId);

        call.enqueue(new Callback<AddToWishListResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AddToWishListResponse> call, Response<AddToWishListResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AddToWishListResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
