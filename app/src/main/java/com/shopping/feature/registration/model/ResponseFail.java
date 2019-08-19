package com.shopping.feature.registration.model;

import com.google.gson.annotations.SerializedName;
import com.shopping.framework.network.Response;

public class ResponseFail {
    public String message;

    public ResponseFail(String msg) {
        this.message = msg;
    }
}
