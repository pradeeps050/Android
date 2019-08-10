package com.shopping.feature.forgatpassword.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordDataSource {
    private static final String TAG = ForgotPasswordDataSource.class.getSimpleName();
    private static  ForgotPasswordDataSource instance;

    public static ForgotPasswordDataSource getInstance() {
        if (instance == null) {
            instance = new ForgotPasswordDataSource();
        }
        return instance;
    }

    public void sendOtp(String mobile, MutableLiveData<ForgotPassword> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).sendOtp(mobile)
                .enqueue(new Callback<ForgotPassword>() {
                    @Override
                    public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            mutableLiveData.postValue(response.body());
                        } else {
                            Log.w(TAG, " >> false");
                            mutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPassword> call, Throwable t) {
                        Log.w(TAG, " >> onFailure");
                        mutableLiveData.postValue(null);
                    }
                });
    }

}
