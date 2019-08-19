package com.shopping.feature.login.ui.login;

import android.support.annotation.Nullable;

import com.shopping.feature.login.data.model.User;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private User user;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    LoginResult(@Nullable User user) {
        this.user = user;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    Boolean isUserVerified() {
        return user.getIsVerified();
    }
    @Nullable
    Integer getError() {
        return error;
    }
}
