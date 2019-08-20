package com.shopping.framework.network;

import java.sql.Statement;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    OkHttpClient.Builder okHttpClient;
    private static RestApiClient instance;
    private Retrofit retrofit;

    public static synchronized RestApiClient getInstance() {
        if(instance == null) {
            instance = new RestApiClient();
        }
        return instance;
    }

    private RestApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RestApi.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RestApi getApi() {
        return retrofit.create(RestApi.class);

    }


}
