package com.shopping.feature.registration.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.shopping.feature.registration.data.OTPRepository;

import okhttp3.ResponseBody;

public class OTPViewModel extends ViewModel {
    private static final String TAG = OTPViewModel.class.getSimpleName();
    private MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();

    private OTPRepository repository;

    public OTPViewModel() {
        Log.d(TAG, ">> OTP View Model >> ");
        repository = OTPRepository.getInstance();
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




}
