package com.shopping.feature.registration.model;

import com.google.gson.annotations.SerializedName;

public class OTP {
    @SerializedName("UserId")
    private Integer userId;
    @SerializedName("Email")
    private String email;
    @SerializedName("Mobile")
    private String mobile;
    @SerializedName("Password")
    private String password;
    @SerializedName("RoleId")
    private Integer roleId;
    @SerializedName("IsVerified")
    private Boolean isVerified;
    @SerializedName("IsActive")
    private Boolean isActive;

    @Override
    public String toString() {
        return "OTP{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", isVerified=" + isVerified +
                ", isActive=" + isActive +
                '}';
    }
}
