package com.shopping.feature.registration.model;

import com.google.gson.annotations.SerializedName;

public class RequestOtpResponse {
    @SerializedName("Id")
    private int id;

    @SerializedName("UserId")
    private int userId;

    @SerializedName("OTP")
    private String otp;

    @SerializedName("ValidTill")
    private String validTill;

    @SerializedName("Verified")
    private Boolean verified;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("IsActive")
    private Boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "RequestOtpResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", otp='" + otp + '\'' +
                ", validTill='" + validTill + '\'' +
                ", verified=" + verified +
                ", mobile='" + mobile + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
