package com.shopping.feature.login.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("expires_in")
    public String expiresIn;

    @SerializedName("refresh_token")
    public String refreshToken;


}
