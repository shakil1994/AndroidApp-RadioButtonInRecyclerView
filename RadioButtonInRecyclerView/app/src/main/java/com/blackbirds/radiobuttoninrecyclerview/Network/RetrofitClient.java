package com.blackbirds.radiobuttoninrecyclerview.Network;

import com.blackbirds.radiobuttoninrecyclerview.Common.Common;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60 * 2, TimeUnit.SECONDS)
                .connectTimeout(60 * 2, TimeUnit.SECONDS)
                .writeTimeout(60 * 2, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Common.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }
}
