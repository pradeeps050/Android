package com.shopping.feature.login.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shopping.feature.login.data.model.LoggedInUser;
import com.shopping.feature.login.data.model.LoginResponse;
import com.shopping.feature.login.data.model.User;
import com.shopping.feature.registration.model.ResponseFail;
import com.shopping.framework.Room.Database.ShoppingRoomDatabase;
import com.shopping.framework.application.AppInstance;
import com.shopping.framework.application.ShoppingApplication;
import com.shopping.framework.model.UserEntity;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;
import com.shopping.framework.network.RestApiServices;
import com.shopping.framework.preference.PreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {
    private static final String TAG = LoginRepository.class.getSimpleName();

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;
    private User loginUser;
    private User us;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;
    //Result<LoggedInUser> result = null;
    Result<User> result;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    private void setLoggedInUser(User user) {
        this.us = user;
    }

    /*public Result<User> login(String username, String password, MutableLiveData<LoginResult> loginResult ) {
        // handle login
        result = dataSource.login(username, password, loginResult);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<User>) result).getData());

        } else {
            Log.d(TAG, " NOT iNSTANCE of RESULT.SUCCESS");
        }

        *//*Result<User> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<User>) result).getData());
        } else {
            Log.d(TAG, " NOT iNSTANCE of RESULT.SUCCESS");

        }*//*
        return result;
    }*/

    /*public User getUser(String token) {
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
    }*/

    public void saveLoginResponse(LoginResponse loginResponse) {
       PreferenceHelper helper = PreferenceHelper.getAppPrefs(AppInstance.getInstance().getContext());
       helper.saveSaveLoginResponse(loginResponse);
    }

    public void login(String email, String password, MutableLiveData<User> mutableLiveData, MutableLiveData<ResponseFail> failMutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).login("password", email, password).
                enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, ">>> Response CODE >>> " + response.code());
                    LoginResponse loginResponse = response.body();
                    if(loginResponse != null) {
                        Log.d(TAG, "Login Response >> Access Token >> " + loginResponse.accessToken);
                        Log.d(TAG, "Login Response >> Token type >> " + loginResponse.tokenType);
                        Log.d(TAG, "Login Response >> Expire In>>  " + loginResponse.expiresIn);
                        Log.d(TAG, "Login Response >> Refresh Token>>  " + loginResponse.refreshToken);
                        saveLoginResponse(loginResponse);
                        Log.d(TAG, ">> Passing token >> ");
                        RestApiBuilder.getNetworkService(RestApi.class)
                                .getUserByToken("Bearer " + loginResponse.accessToken)
                                .enqueue(new Callback<User>() {

                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if (response.isSuccessful()) {
                                            Log.d(TAG, ">>> Response CODE >>> " + response.code());
                                            User user = response.body();
                                            if (user != null) {
                                                Log.i(TAG, ">> User Details >>> " + user.toString());
                                                mutableLiveData.postValue(user);
                                                saveUserInDatabase(user);
                                            } else {
                                                Log.w(TAG, ">> User is NULL >>");
                                                mutableLiveData.postValue(user);
                                            }
                                        } else {
                                            Log.e(TAG, ">> Not access user profile >>");
                                            mutableLiveData.postValue(null);
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Log.e(TAG, ">> User profile onFail >> ");
                                        failMutableLiveData.postValue(new ResponseFail("Response Fail"));
                                    }
                                });

                    } else {
                        Log.w(TAG, ">> Login Response is NULL");
                        failMutableLiveData.postValue(new ResponseFail("Response Fail"));
                    }
                } else {
                    Log.w(TAG, ">> Response is FALSE while login >> ");
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("Message");
                        Log.w(TAG, ">> + " +  message);
                        failMutableLiveData.postValue(new ResponseFail(message));
                    } catch (IOException e) {
                        Log.e(TAG, ">> " + e.getMessage());
                    } catch (JSONException e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, ">> Login Fail >>");
                failMutableLiveData.postValue(new ResponseFail("Response Fail"));
            }
        });
    }

    public void saveUserInDatabase(User user) {
        Log.i(TAG, ">> saving in database >> ");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserID(user.getUserId().intValue());
        userEntity.setEmail(user.getEmail());
        userEntity.setMobile(user.getMobile());
        userEntity.setFullName(user.getUserDetails()!= null && user.getUserDetails().size()!= 0 ? user.getUserDetails().get(0).getFullName() : null);
        ShoppingApplication.getInstance().getExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                ShoppingRoomDatabase db = ShoppingRoomDatabase.getInstance(AppInstance.getInstance().getContext());
                db.userDao().insetUserDetails(userEntity);
            }
        });
    }
}
