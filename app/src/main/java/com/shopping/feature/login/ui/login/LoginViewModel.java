package com.shopping.feature.login.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import com.shopping.R;
import com.shopping.feature.login.data.LoginRepository;
import com.shopping.feature.login.data.Result;
import com.shopping.feature.login.data.model.User;
import com.shopping.feature.registration.model.ResponseFail;
import com.shopping.framework.network.NetworkState;


public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<NetworkState> networkState = new MutableLiveData<>();
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseFail> failMutableLiveData = new MutableLiveData<>();
    private LoginRepository loginRepository;
    Result<User> result;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    LiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    LiveData<ResponseFail> getFailResponse(){return failMutableLiveData; }

    public void login(String username, String password) {
        loginRepository.login(username, password, userMutableLiveData, failMutableLiveData);
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        /*if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }*/
        if (username == null) {
            return false;
        }
        boolean f = Patterns.EMAIL_ADDRESS.matcher(username).matches();
        //Log.d(TAG, ">> " + String.valueOf("email " + f));
        return f;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
