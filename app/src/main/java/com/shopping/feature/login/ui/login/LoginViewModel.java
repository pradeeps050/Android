package com.shopping.feature.login.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Patterns;

import com.shopping.R;
import com.shopping.feature.login.data.LoginRepository;
import com.shopping.feature.login.data.Result;
import com.shopping.feature.login.data.model.LoggedInUser;
import com.shopping.feature.login.data.model.User;
import com.shopping.framework.application.AppInstance;
import com.shopping.framework.logger.Logger;
import com.shopping.framework.preference.PreferenceHelper;


public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Logger.d(TAG, ">>>> login");
        loginRepository.login(username, password);
        /*PreferenceHelper helper = PreferenceHelper.getAppPrefs(AppInstance.getInstance().getContext());
        String token = helper.getLoginToken();
        Log.d(TAG, " TOKEN >> " + token);
        if (token != null) {
            loginRepository.getUser(token);
        }*/

        /*new AsyncTask() {
            Result<LoggedInUser> result;

            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d(TAG, ">>> doInBackground ");
                result = loginRepository.login(username, password);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.d(TAG, ">>> onPostExecute ");
                super.onPostExecute(o);
                if (result instanceof Result.Success) {
                    LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
                    loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }

            }
        }.execute();*/


        //Result<LoggedInUser> result = loginRepository.login(username, password);

        /*if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }*/
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

    /*public void login(User user) {
        Logger.d(TAG, ">>>> login");
        Result<LoggedInUser> result = loginRepository.login(user);
        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }




       *//* new AsyncTask() {
            Result<LoggedInUser> result;

            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d(TAG, ">>> doInBackground ");
                result = loginRepository.login(user);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.d(TAG, ">>> onPostExecute ");
                super.onPostExecute(o);
                if (result instanceof Result.Success) {
                    LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
                    loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }

            }
        }.execute();*//*

    }*/
}
