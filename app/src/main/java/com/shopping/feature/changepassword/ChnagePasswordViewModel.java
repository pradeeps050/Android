package com.shopping.feature.changepassword;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shopping.feature.changepassword.data.ChangePasswordDataSource;

import okhttp3.ResponseBody;

public class ChnagePasswordViewModel extends ViewModel {
    private ChangePasswordDataSource dataSource;
    MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();

    public ChnagePasswordViewModel() {
        dataSource = ChangePasswordDataSource.getInstance();
    }

    public LiveData<ResponseBody> getMutableData() {
        return mutableLiveData;
    }

    public void validateOtp(int userId, String otp) {
        dataSource.validateOtp(userId, otp, mutableLiveData);
    }
}
