package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.CategoriesApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.CategoriesResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class CategoriesViewModel extends ViewModel {

    private MutableLiveData<CategoriesResponse> mutableLiveData;

    public LiveData<CategoriesResponse> getCategories() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadCategories();
        }
        return mutableLiveData;
    }

    private void loadCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriesApi api = retrofit.create(CategoriesApi.class);

        Call<CategoriesResponse> call = api.getCategories();

        call.enqueue(new Callback<CategoriesResponse>() {

            @EverythingIsNonNull
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
