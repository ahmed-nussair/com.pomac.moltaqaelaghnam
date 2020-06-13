package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.ShowAdvertisementApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.ShowAdvertisementResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class ShowAdvertisementViewModel extends ViewModel {

    private MutableLiveData<ShowAdvertisementResponse> mutableLiveData;

    public LiveData<ShowAdvertisementResponse> showAdvertisement(int adId) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            doShowAdvertisement(adId);
        }
        return mutableLiveData;
    }

    private void doShowAdvertisement(int adId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShowAdvertisementApi api = retrofit.create(ShowAdvertisementApi.class);

        Call<ShowAdvertisementResponse> call = api.showAdvertisement(adId);

        call.enqueue(new Callback<ShowAdvertisementResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ShowAdvertisementResponse> call, Response<ShowAdvertisementResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ShowAdvertisementResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });

    }
}
