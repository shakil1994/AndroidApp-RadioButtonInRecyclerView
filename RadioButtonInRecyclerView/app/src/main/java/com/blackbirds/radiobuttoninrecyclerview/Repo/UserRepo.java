package com.blackbirds.radiobuttoninrecyclerview.Repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blackbirds.radiobuttoninrecyclerview.Model.UserResponse;
import com.blackbirds.radiobuttoninrecyclerview.Network.ApiService;
import com.blackbirds.radiobuttoninrecyclerview.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {
    private final ApiService apiService;

    public UserRepo() {
        this.apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<UserResponse>> getUserResponse(){
        MutableLiveData<List<UserResponse>> data = new MutableLiveData();
        apiService.getUsrResponse().enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                Log.e("UserRepo", "onResponse" + response.code());
                if (response.isSuccessful()){
                    data.setValue(response.body());
                }
                else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("UserRepo", "onFailure" + t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }
}
