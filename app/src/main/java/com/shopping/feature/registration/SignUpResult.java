package com.shopping.feature.registration;

import android.support.annotation.Nullable;

public class SignUpResult {

    @Nullable
    private Integer error;
    @Nullable
    private String result;

    public SignUpResult(@Nullable Integer error) {
        this.error = error;
    }

    public SignUpResult(String result) {
        this.result = result;
    }

    @Nullable
    public Integer getSignUpError() {
        return error;
    }

    @Nullable
    public String getResult() {
        return result;
    }
}
