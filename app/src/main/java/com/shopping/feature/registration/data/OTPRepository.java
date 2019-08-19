package com.shopping.feature.registration.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shopping.feature.registration.model.OTP;
import com.shopping.feature.registration.model.RequestOtpResponse;
import com.shopping.feature.registration.model.ValidateOtp;
import com.shopping.framework.Room.Database.ShoppingRoomDatabase;
import com.shopping.framework.application.AppInstance;
import com.shopping.framework.logger.Logger;
import com.shopping.framework.model.UserEntity;
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

    public void requestOtp(int userId, MutableLiveData<RequestOtpResponse> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).otpRequest(userId)
                .enqueue(new Callback<RequestOtpResponse>() {
                    @Override
                    public void onResponse(Call<RequestOtpResponse> call, Response<RequestOtpResponse> response) {
                        if (response.isSuccessful() && response.code()== 200) {
                            RequestOtpResponse requestOtpResponse = response.body();
                            mutableLiveData.postValue(requestOtpResponse);
                            /*

                            try {
                                Log.i(TAG, ">> OTP response " + response.body().string());
                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }*/
                        } else {
                            Log.e(TAG, ">> response is FALSE ");
                            mutableLiveData.postValue(null);
                            /*try {
                                Log.i(TAG, ">> OTP response " + response.body().string());

                            } catch (IOException e) {
                                Log.e(TAG, ">> " + e.getMessage());
                            }*/
                        }
                    }
                    @Override
                    public void onFailure(Call<RequestOtpResponse> call, Throwable t) {
                        Log.e(TAG, ">> response is FALSE ");
                        mutableLiveData.postValue(null);

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

    public void verifyOtp(int userId, int otp, MutableLiveData<OTP> liveData) {
        RestApiBuilder.getNetworkService(RestApi.class).verifyOtp(userId, otp)
                .enqueue(new Callback<OTP>() {
                    @Override
                    public void onResponse(Call<OTP> call, Response<OTP> response) {
                        if (response.isSuccessful() && response.code()== 200) {
                            OTP otpResponse = response.body();
                            liveData.postValue(otpResponse);
                            updateDatabase(response.body());
                            Log.i(TAG, ">> OTP " + otpResponse.toString());
                        } else {
                            Log.e(TAG, ">> response is FALSE ");
                            liveData.postValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<OTP> call, Throwable t) {
                        Log.e(TAG, ">> response is FALSE ");
                        liveData.postValue(null);
                    }
                });
    }

    public void validateOtp(int userId, String otp, MutableLiveData<ValidateOtp> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).validateOtp(userId, otp)
                .enqueue(new Callback<ValidateOtp>() {
                    @Override
                    public void onResponse(Call<ValidateOtp> call, Response<ValidateOtp> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Logger.d(TAG, ">> otp validate while forgot password true");
                            mutableLiveData.postValue(response.body());
                        } else {
                            Logger.i(TAG, ">> otp validate while forgot password false");
                            mutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ValidateOtp> call, Throwable t) {
                        Log.d(TAG, ">> onFailure");
                        mutableLiveData.postValue(null);

                    }
                });
    }

    private void updateDatabase(OTP otp) {
        ShoppingRoomDatabase db = ShoppingRoomDatabase.getInstance(AppInstance.getInstance().getContext());
        UserEntity userEntity = new UserEntity();
        userEntity.setIsVerified(otp.getVerified() == true? 1 : 0);
    }
}
