package com.shopping.feature.registration.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.shopping.feature.registration.data.OTPRepository;
import com.shopping.feature.registration.model.OTP;
import com.shopping.feature.registration.model.RequestOtpResponse;
import com.shopping.feature.registration.model.ValidateOtp;

public class OTPViewModel extends ViewModel {
    private static final String TAG = OTPViewModel.class.getSimpleName();
    private MutableLiveData<OTP> otpLiveData = new MutableLiveData<>();
    private MutableLiveData<RequestOtpResponse> otpRequestLiveData = new MutableLiveData<>();
    private MutableLiveData<ValidateOtp> responseBodyLiveData = new MutableLiveData<>();

    private OTPRepository repository;

    public OTPViewModel() {
        Log.d(TAG, ">> OTP View Model >> ");
        repository = OTPRepository.getInstance();
    }

    public LiveData<OTP> getMutable() {
        return otpLiveData;
    }

    public LiveData<RequestOtpResponse> getOTPRequestData() {
        return otpRequestLiveData;
    }

    public LiveData<ValidateOtp> getResponseLiveData(){return responseBodyLiveData;}

    public void requestOtp(int userId) {
        repository.requestOtp(userId, otpRequestLiveData);
    }

    public void resendOtp(int userId) {
        repository.resendOtp(userId);
    }

    //Login otp
    public void verifyOtp(int userId, int otp) {
        repository.verifyOtp(userId, otp, otpLiveData);
    }

    //Forget password
    public void validateOtp(int userId, String otp) {
        repository.validateOtp(userId, otp, responseBodyLiveData);
    }




}
