package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.UpdateProfileApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.UpdateProfileResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class UpdateProfileViewModel extends ViewModel {

    private MutableLiveData<UpdateProfileResponse> mutableLiveData;

    public LiveData<UpdateProfileResponse> updateProfile(String token, String name) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            doUpdateProfile(token, name);
        }
        return mutableLiveData;
    }

    private void doUpdateProfile(String token, String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UpdateProfileApi api = retrofit.create(UpdateProfileApi.class);

        Call<UpdateProfileResponse> call = api.updateProfile(token, name);

        call.enqueue(new Callback<UpdateProfileResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
