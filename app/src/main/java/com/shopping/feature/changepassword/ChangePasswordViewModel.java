package com.shopping.feature.changepassword;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shopping.feature.changepassword.data.ChangePasswordDataSource;

import okhttp3.ResponseBody;

public class ChangePasswordViewModel extends ViewModel {
    private ChangePasswordDataSource dataSource;
    private MutableLiveData<ResponseBody> mutableLiveData = new MutableLiveData<>();

    public ChangePasswordViewModel() {
        dataSource = ChangePasswordDataSource.getInstance();
    }

    public LiveData<ResponseBody> getMutableData() {
        return mutableLiveData;
    }

    public void updatePassword(int userId, String password) {
        dataSource.updatePassword(userId, password, mutableLiveData);
    }
}
