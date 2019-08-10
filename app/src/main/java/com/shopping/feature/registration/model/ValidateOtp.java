package com.shopping.feature.registration.model;

import com.google.gson.annotations.SerializedName;

/**
 * Parse response of validate opt api(after calling forgot password, verify otp)
 */
public class ValidateOtp {
    @SerializedName("UserId")
    private Integer userId;

    @SerializedName("IsVerified")
    private Boolean isVerified;

    @SerializedName("OTP")
    private String otp;

    @SerializedName("IsActive")
    private Boolean isActive;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ValidateOtp{" +
                "userId=" + userId +
                ", isVerified=" + isVerified +
                ", otp='" + otp + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
