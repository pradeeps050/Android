package com.shopping.feature.login.data;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.shopping.feature.login.data.model.LoggedInUser;
import com.shopping.feature.login.data.model.LoginResponse;
import com.shopping.feature.login.data.model.User;
import com.shopping.framework.application.AppInstance;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;
import com.shopping.framework.preference.PreferenceHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final String TAG = LoginDataSource.class.getSimpleName();
    private User user;

    public Result<User> login(String username, String password) {
        //User user;
        try {
            // TODO: handle loggedInUser authentication
            /*LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");*/
            RestApiBuilder.getNetworkService(RestApi.class).login("password", "ps@gmail.com",
                    "Viraj@2019")
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                Log.i(TAG, ">>> On MAIN THREAD");
                            }else {
                                Log.i(TAG, ">>> NOT In Main Thred");
                            }
                            if (response.isSuccessful()) {
                                Log.d(TAG, ">>> Response CODE >>> " + response.code());
                                LoginResponse loginResponse = response.body();
                                if(loginResponse != null) {
                                    Log.d(TAG, "Login Response >> Access Token >> " + loginResponse.accessToken);
                                    Log.d(TAG, "Login Response >> Token type >> " + loginResponse.tokenType);
                                    Log.d(TAG, "Login Response >> Expire In>>  " + loginResponse.expiresIn);
                                    Log.d(TAG, "Login Response >> Refresh Token>>  " + loginResponse.refreshToken);
                                    user = saveLoginResponse(loginResponse);

                                } else {
                                    Log.w(TAG, ">> Login Response is NULL");
                                }
                            } else {
                                Log.w(TAG, ">> Response is UNSuccessful");
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.w(TAG, "On Failure");

                        }
                    });
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public User getUser(String token) {
        Log.d(TAG, ">> getUser>> ");
        User user = null;
        RestApiBuilder.getNetworkService(RestApi.class)
                .getUserByToken("Bearer " + token)
                .enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, ">>> Response CODE>>> " + response.code());
                            User user = response.body();
                            if (user != null) {
                                Log.i(TAG, ">> User Details >>> " + user.toString());

                            } else {
                                Log.w(TAG, "User is NULL ");
                            }

                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
        return user;
    }

    public User saveLoginResponse(LoginResponse loginResponse) {
        Context context =  AppInstance.getInstance().getContext();
        User user = null;
        if (context != null) {
            PreferenceHelper helper = PreferenceHelper.getAppPrefs(AppInstance.getInstance().getContext());
            helper.saveSaveLoginResponse(loginResponse);
            user = getUser(loginResponse.accessToken);
        } else {
            Log.w(TAG, ">> App context is NULL");
        }
        return user;
    }
    public void logout() {
        // TODO: revoke authentication
    }
}
