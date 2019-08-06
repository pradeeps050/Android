package com.shopping.feature.registration.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPRepository {
    private static final String TAG = OTPRepository.class.getSimpleName();
    private static volatile OTPRepository instance;

    public static OTPRepository getInstance() {
        if (instance == null) {
            instance = new OTPRepository();
        }
        return instance;
    }

    public void requestOtp(int userId, MutableLiveData<ResponseBody> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).otpRequest(userId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.code()== 200) {
                            try {
                                Log.i(TAG, ">> OTP response " + response.body().string());
                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }
                        } else {
                            Log.e(TAG, ">> response is FALSE ");
                            try {
                                Log.i(TAG, ">> OTP response " + response.body().string());

                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, ">> response is FALSE ");

                    }
                });
    }

    public void resendOtp(int userId) {
        RestApiBuilder.getNetworkService(RestApi.class).reSendOtp(userId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.code()== 200) {
                            try {
                                Log.i(TAG, ">> RE OTP response " + response.body().string());

                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }
                        } else {
                            Log.e(TAG, ">> response is FALSE ");
                            try {
                                Log.i(TAG, ">> RE OTP response " + response.body().string());

                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, ">> response is FALSE ");

                    }
                });
    }

    public void verifyOtp(int userId, int otp) {
        RestApiBuilder.getNetworkService(RestApi.class).verifyOtp(userId, otp)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.code()== 200) {
                            try {
                                Log.i(TAG, ">> OTP response " + response.body().string());

                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }
                        } else {
                            Log.e(TAG, ">> response is FALSE ");
                            try {
                                //check null pointer
                                Log.i(TAG, ">> OTP response " + response.body().string());

                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, ">> response is FALSE ");

                    }
                });
    }
}
