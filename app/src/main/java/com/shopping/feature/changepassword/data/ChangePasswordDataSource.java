package com.shopping.feature.changepassword.data;

import android.arch.lifecycle.MutableLiveData;

import com.shopping.framework.logger.Logger;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import okhttp3.ResponseBody;
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

    public void updatePassword(int userId, String pasword, MutableLiveData<ResponseBody> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).updatePassword(userId, pasword)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Logger.d(TAG, ">> Change password true");
                            mutableLiveData.postValue(response.body());
                        } else {
                            Logger.d(TAG, ">> Change password false");
                            mutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Logger.d(TAG, ">> Change password onFail");
                        mutableLiveData.postValue(null);

                    }
                });
    }

}
