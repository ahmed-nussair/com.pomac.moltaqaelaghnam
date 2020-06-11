package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.FilterAdvertisementsApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.FilterAdvertisementsResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class FilterAdvertisementsViewModel extends ViewModel {

    private MutableLiveData<FilterAdvertisementsResponse> mutableLiveData;

    public LiveData<FilterAdvertisementsResponse> filterAdvertisements(int areaId, int mainId) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            doFilterAdvertisements(areaId, mainId);
        }
        return mutableLiveData;
    }

    private void doFilterAdvertisements(int areaId, int mainId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FilterAdvertisementsApi api = retrofit.create(FilterAdvertisementsApi.class);

        Call<FilterAdvertisementsResponse> call = api.filterAdvertisements(areaId, mainId);

        call.enqueue(new Callback<FilterAdvertisementsResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<FilterAdvertisementsResponse> call, Response<FilterAdvertisementsResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<FilterAdvertisementsResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
