package com.pomac.moltaqaelaghnam.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.model.apimodels.UserAccountApi;
import com.pomac.moltaqaelaghnam.model.responsemodels.UserAccountResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class UserAccountViewModel extends ViewModel {

    private MutableLiveData<UserAccountResponse> mutableLiveData;

    public LiveData<UserAccountResponse> getUserAccount(String token) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadUserAccount(token);
        }
        return mutableLiveData;
    }

    private void loadUserAccount(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAccountApi api = retrofit.create(UserAccountApi.class);

        Call<UserAccountResponse> call = api.getUserAccount(token);

        call.enqueue(new Callback<UserAccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                Log.e(Globals.TAG, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
