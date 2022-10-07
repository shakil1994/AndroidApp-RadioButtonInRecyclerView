package com.blackbirds.radiobuttoninrecyclerview.Network;

import com.blackbirds.radiobuttoninrecyclerview.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<UserResponse>> getUsrResponse();
}
