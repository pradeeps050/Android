package com.shopping.framework.network;


import com.shopping.feature.forgatpassword.data.ForgotPassword;
import com.shopping.feature.login.data.model.LoginResponse;
import com.shopping.feature.login.data.model.User;
import com.shopping.feature.product.model.Model;
import com.shopping.feature.registration.model.OTP;
import com.shopping.feature.registration.model.SignUpResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    String API_BASE_URL = "http://3.220.157.39:91"; //"https://api.stackexchange.com/2.2/";

    @GET("answers")
    Call<Model> getAnswers(@Query("page") int page, @Query("pagesize") int pagesize, @Query("site") String site);

    @FormUrlEncoded
    @POST("/Register")
    Call<SignUpResponse> signUp(
            @Field("Email") String email,
            @Field("Mobile") String phone,
            @Field("Password") String password);


    @FormUrlEncoded
    @POST("/Login")
    Call<LoginResponse> login(@Field("grant_type") String grant,
                              @Field("username") String username,
                              @Field("password") String password);

    @GET("/UsersByToken")
    Call<User> getUserByToken(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/sendotpfromuser")
    Call<ResponseBody> otpRequest(@Field("UserId") int userId);

    @FormUrlEncoded
    @POST("/resendotp")
    Call<ResponseBody> reSendOtp(@Field("UserId") int userId);

    @FormUrlEncoded
    @POST("/VerifyUser")
    Call<OTP> verifyOtp(@Field("UserId") int userId, @Field("OTP") int otp);

    @FormUrlEncoded
    @POST("/sendotp")
    Call<ForgotPassword> sendOtp(@Field("Mobile") String mobile);

    @FormUrlEncoded
    @POST("/validateotp")
    Call<ResponseBody> validateOtp(@Field("UserId") int userId, @Field("OTP") String otp);

    @FormUrlEncoded
    @POST("/UpdatePassword")
    Call<ResponseBody> updatePassword(@Field("UserId") int userId, @Field("Password") String password);


    //response is userid and otp and call validateotp field papa UserId, OTP

    // after if true>>  api UpdatePassword filed UserId Password

}













