package com.shopping.framework.application;

public class AppInstance {
    private static ShoppingApplication context;

    public void init(ShoppingApplication appContext) { // only call from App!
        context = appContext;
    }

    public  ShoppingApplication getContext() {
        return context;
    }

    private static AppInstance mInstance = new AppInstance();
    public static AppInstance getInstance() {
        synchronized (AppInstance.class) {
            if (mInstance == null) {
                mInstance = new AppInstance();
            }
        }
        return mInstance;
    }
}
