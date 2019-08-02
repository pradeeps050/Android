package com.shopping.framework.network;


import com.shopping.BuildConfig;
import com.shopping.feature.login.data.Result;
import com.shopping.feature.login.data.model.LoggedInUser;
import com.shopping.feature.login.data.model.LoginResponse;
import com.shopping.feature.login.data.model.User;
import com.shopping.feature.registration.SignUpResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {
    String API_BASE_URL = "http://3.220.157.39:91";

    @FormUrlEncoded
    @POST("/Register")
    Call<ResponseBody> signUp(
            @Field("Email") String email,
            @Field("Mobile") String phone,
            @Field("Password") String password);


    @FormUrlEncoded
    @POST("/Login")
    Call<LoginResponse> login(@Field("grant_type") String grant,
                              @Field("username") String username,
                              @Field("password") String password);


    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/Login")
    Call<Result<LoggedInUser>> loginUser(@Field("User") User user);



    @GET("/UsersByToken")
    Call<User> getUserByToken(@Header("Authorization") String token);
}
