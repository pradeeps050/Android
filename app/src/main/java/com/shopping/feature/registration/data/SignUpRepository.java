package com.shopping.feature.registration.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.shopping.feature.login.ui.login.LoginActivity;
import com.shopping.feature.registration.SignUpResult;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;
import com.shopping.framework.network.RestApiServices;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpRepository {
    private static final String TAG = SignUpRepository.class.getSimpleName();

    private static volatile SignUpRepository instance;

    public static SignUpRepository getInstance() {
        if (instance == null) {
            instance = new SignUpRepository();
        }
        return instance;
    }

    public SignUpResult signUp(String email, String phone, String password) {
        RestApiBuilder.getNetworkService(RestApi.class).signUp(
                "admin@virajgroup.org", "+91-6394007599", "Viraj@2019")
        .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, ">>> Response is successfil");
                    try {
                        Log.d(TAG, ">>> Response is " + response.body().string());
                    } catch (IOException e) {
                        Log.e(TAG, ">>>> " + e.getMessage());
                    }
                } else {
                    Log.d(TAG, ">>> Response is NOT successfull");
                    Log.d(TAG, ">>> Response CODE >>> " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, ">>> Response FAIL " );

            }
        });

        /*RestApiBuilder.getNetworkService(RestApi.class).signUp("abc@gmail.com", "+91 9087654321", "Abc@2345").enqueue(new Callback<SignUpResult>() {
            @Override
            public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                if (response.body() != null) {
                    Log.d(TAG, ">> Response " + response.message());

                }

            }

            @Override
            public void onFailure(Call<SignUpResult> call, Throwable t) {

            }
        });*/



        /*Call<SignUpResult> call = RestApiClient.getInstance().getApi().signUp(email, phone, password);
        call.enqueue(new Callback<SignUpResult>() {
            @Override
            public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                Log.d(TAG, "onResponse >> " + String.valueOf(response.code()));
                SignUpResult result = response.body();

                Log.d(TAG, ">> SignUp Result " + result.getResult());

            }

            @Override
            public void onFailure(Call<SignUpResult> call, Throwable t) {
                Log.d(TAG, "onFailure >> " );

            }
        });*/




        return null;
    }
}
