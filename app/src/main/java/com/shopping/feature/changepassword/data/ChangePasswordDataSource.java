package com.shopping.feature.changepassword.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordDataSource {
    private static final String TAG = ChangePasswordDataSource.class.getSimpleName();

    private static ChangePasswordDataSource instance;

    public static ChangePasswordDataSource getInstance() {
        if (instance == null) {
            instance = new ChangePasswordDataSource();
        }
        return instance;
    }

    public void validateOtp(int userId, String otp, MutableLiveData<ResponseBody> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).validateOtp(userId, otp)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            mutableLiveData.postValue(response.body());
                        } else {
                            mutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, ">> onFailure");

                    }
                });
    }

}
