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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

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
