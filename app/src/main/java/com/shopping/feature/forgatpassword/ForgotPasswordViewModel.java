package com.shopping.feature.forgatpassword;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shopping.feature.forgatpassword.data.ForgotPassword;
import com.shopping.feature.forgatpassword.data.ForgotPasswordDataSource;

public class ForgotPasswordViewModel extends ViewModel {
    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();
    private ForgotPasswordDataSource dataSource;
    private MutableLiveData<ForgotPassword> mutableLiveData = new MutableLiveData<>();

    public ForgotPasswordViewModel() {
        dataSource = ForgotPasswordDataSource.getInstance();
    }

    public LiveData<ForgotPassword> getLiveData() {
        return mutableLiveData;
    }

    public void sendOtp(String mobile) {
        dataSource.sendOtp(mobile, mutableLiveData);
    }

}
