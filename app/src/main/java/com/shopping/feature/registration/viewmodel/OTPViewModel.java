package com.shopping.feature.registration.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.shopping.feature.registration.data.OTPRepository;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPViewModel extends ViewModel {
    private static final String TAG = OTPViewModel.class.getSimpleName();
    private MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();

    private OTPRepository repository;

    public OTPViewModel() {
        Log.d(TAG, ">> OTP View Model >> ");
        repository = OTPRepository.getInstance();
    }

    public LiveData<ResponseBody> getMutable() {
        return mutableLiveData;
    }

    public void requestOtp(int userId) {
        repository.requestOtp(userId, mutableLiveData);
    }

    public void resendOtp(int userId) {
        repository.resendOtp(userId);
    }

    public void verifyOtp(int userId, int otp) {
        repository.verifyOtp(userId, otp);
    }

    public void validateOtp(int userId, String otp) {
        repository.validateOtp(userId, otp, mutableLiveData);
    }




}
