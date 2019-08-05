package com.shopping.feature.login.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.shopping.feature.login.data.model.LoggedInUser;
import com.shopping.feature.login.data.model.LoginResponse;
import com.shopping.feature.login.data.model.User;
import com.shopping.feature.login.ui.login.LoginActivity;
import com.shopping.feature.login.ui.login.LoginResult;
import com.shopping.feature.registration.SignUpResult;
import com.shopping.feature.registration.model.ResponseFail;
import com.shopping.framework.Executors.AppExecutors;
import com.shopping.framework.Room.Repository.UserRepository;
import com.shopping.framework.application.AppInstance;
import com.shopping.framework.model.UserEntity;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;
import com.shopping.framework.network.RestApiClient;
import com.shopping.framework.network.RestApiServices;
import com.shopping.framework.preference.PreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

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
       } else {
           Log.w(TAG, ">> App context is NULL");
       }
       return user;
    }

    public void login(String email, String password,
                      MutableLiveData<User> mutableLiveData, MutableLiveData<ResponseFail> failMutableLiveData) {
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
                                            Log.d(TAG, ">>> Response CODE>>> " + response.code());
                                            User user = response.body();
                                            if (user != null) {
                                                Log.i(TAG, ">> User Details >>> " + user.toString());
                                                mutableLiveData.postValue(user);
                                                saveUserInDatabase(user);
                                            } else {
                                                Log.w(TAG, ">> User is NULL >>");
                                            }
                                        } else {
                                            Log.e(TAG, ">> Not access user profile >>");
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Log.e(TAG, ">> User profile onFail >> ");
                                    }
                                });

                    } else {
                        Log.w(TAG, ">> Login Response is NULL");
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
            }
        });
    }

    public void saveUserInDatabase(User user) {
        Log.i(TAG, ">> saving in database >> ");
        UserRepository userRepository = new UserRepository(AppInstance.getInstance().getContext());
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFullName(user.getUserDetails()!= null && user.getUserDetails().size()!= 0 ? user.getUserDetails().get(0).getFullName() : null);
        userEntity.setMobile(user.getMobile().intValue());
        userRepository.insert(userEntity);
    }

    /*public Result<LoggedInUser> login(User user) {
        Log.d(TAG, ">>> loginUser");

        RestApiBuilder.getNetworkService(RestApi.class).loginUser(new User("8090404700", "Shani@1234", "password"))
                .enqueue(new Callback<Result<LoggedInUser>>() {
                    @Override
                    public void onResponse(Call<Result<LoggedInUser>> call, Response<Result<LoggedInUser>> response) {
                        Log.d(TAG, ">>>> Response code" + String.valueOf(response.code()));
                        Log.d(TAG, ">>>> Response message " + response.message());
                        Log.d(TAG, ">>> Response " + response.isSuccessful());
                        result = response.body();
                        if (result instanceof Result.Success) {
                            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<LoggedInUser>> call, Throwable t) {
                        Log.d(TAG, "onFailure ");

                    }
                });
        return result;

    }*/
}
