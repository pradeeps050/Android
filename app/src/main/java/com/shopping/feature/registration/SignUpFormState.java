package com.shopping.feature.registration;

import android.support.annotation.Nullable;

/**
 * user credential validation while sign up new user.
 */

public class SignUpFormState {
    @Nullable
    private Integer email;
    @Nullable
    private Integer phone;
    @Nullable
    private Integer password;

    private boolean isDataValid;

    public SignUpFormState(@Nullable Integer email, @Nullable Integer phone, @Nullable Integer password) {
        this.email = email;
        this.phone = phone;
        this.password  = password;
        this.isDataValid = false;
    }

    public  SignUpFormState(boolean isDataValid) {
        this.email = null;
        this.phone = null;
        this.password = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getEmailError() {
        return email;
    }

    @Nullable
    Integer getPhoneError() {
        return phone;
    }

    @Nullable
    Integer getPasswordError() {
        return password;
    }

    boolean isDataValid() {
        return isDataValid;
    }

}
