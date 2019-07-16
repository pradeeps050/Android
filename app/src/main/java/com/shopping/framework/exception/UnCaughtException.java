package com.shopping.framework.exception;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.shopping.BuildConfig;
import com.shopping.framework.logger.Logger;




public  class UnCaughtException implements Thread.UncaughtExceptionHandler {
    private final Activity myContext;
    public UnCaughtException(Activity context) {
        myContext = context;
    }
    public UnCaughtException() {
        myContext  = null;
    }

    
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        pushHomeScreen();
        if (BuildConfig.DEBUG) {
            Logger.error(throwable.toString());
        }
    }


    private void pushHomeScreen() {

    }


}
